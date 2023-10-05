package com.prplmnstr.invoiceapp.viewmodel

import SharedPreferencesManager
import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.prplmnstr.invoiceapp.model.User
import com.prplmnstr.invoiceapp.utils.Constants.Companion.USERS_COLLECTION
import com.prplmnstr.invoiceapp.utils.Event
import kotlinx.coroutines.launch

class LoginViewModel(private val application: Application) : AndroidViewModel(application){
    private val auth = FirebaseAuth.getInstance()
    val sharedPreferencesManager = SharedPreferencesManager(application)
    private val statusMessage = MutableLiveData<Event<String>>()
    val firestoreDB = FirebaseFirestore.getInstance()

    val message : LiveData<Event<String>>
        get() = statusMessage

     val _navigateToDashboard = MutableLiveData<Boolean>()
    val navigateToDashboard: LiveData<Boolean>
        get() = _navigateToDashboard

    private val _showProgressDialog = MutableLiveData<Boolean>()
    val showProgressDialog: LiveData<Boolean>
        get() = _showProgressDialog


    val inputBusinessName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    val inputPassword = MutableLiveData<String>()


    fun loginWithEmailAndPassword(

    ) {
        auth.signInWithEmailAndPassword(inputEmail.value!!,inputPassword.value!!)
            .addOnSuccessListener { authResult ->
                statusMessage.value = Event("Logged in  successfully.")
                _showProgressDialog.value = false
                _navigateToDashboard.value = true
            }
            .addOnFailureListener { exception ->
                statusMessage.value = Event(exception.message.toString())
                _showProgressDialog.value = false
            }
    }

    fun checkEmailExistsInFirestoreAndLogin() {

       if(inputEmail.value ==null || !inputEmail.value!!.endsWith("@gmail.com")){
            statusMessage.value = Event("Please enter valid email.\n(example@gmail.com)")
        }else if(inputPassword.value==null || inputPassword.value!!.length<8){
            statusMessage.value = Event("Password length should be greater than or equal to 8.")
        }else {
           val email = inputEmail.value!!.trim()


           _showProgressDialog.value = true
           val usersCollection = firestoreDB.collection(USERS_COLLECTION)

           viewModelScope.launch {
               usersCollection.document(email).get()
                   .addOnSuccessListener { documentSnapshot ->
                       if (documentSnapshot.exists()) {
                           // User with the entered email exists in Firestore
                           val username = documentSnapshot.getString("userName")
                           val invoiceNumber = documentSnapshot.get("invoiceNumber")
                           val quotationNumber = documentSnapshot.get("quotationNumber")

                           // Store the username in SharedPreferences

                           if (username != null) {
                               sharedPreferencesManager.saveUserLoggedIn(true)
                               sharedPreferencesManager.saveUserName(username)
                               sharedPreferencesManager.saveInvoiceNumber(invoiceNumber as Long)
                               sharedPreferencesManager.savequotationNumber(quotationNumber as Long)

                           }

                           loginWithEmailAndPassword()

                       } else {
                           statusMessage.value = Event("User with the entered email does not exist")
                           _showProgressDialog.value = false

                       }
                   }
                   .addOnFailureListener { exception ->
                       statusMessage.value = Event(exception.message.toString())
                       _showProgressDialog.value = false

                   }
           }

       }
    }

    fun signUpUser() {
        if(inputBusinessName.value ==null){
            statusMessage.value = Event("Please enter business name")
        }else if(inputEmail.value ==null || !inputEmail.value!!.endsWith("@gmail.com")){
            statusMessage.value = Event("Please enter valid email.\n(example@gmail.com)")
        }else if(inputPassword.value==null || inputPassword.value!!.length<8){
            statusMessage.value = Event("Password length should be greater than or equal to 8.")
        }else {
            _showProgressDialog.value = true
            val usersCollection = firestoreDB.collection(USERS_COLLECTION)

            viewModelScope.launch {
                usersCollection.document(inputEmail.value!!.trim()).get()
                    .addOnSuccessListener { documentSnapshot ->
                        if (documentSnapshot.exists()) {
                            // User with the entered email exists in Firestore
                            statusMessage.value =
                                Event("User account already exist")
                            _showProgressDialog.value = false

                        } else {
                            val businessName = inputBusinessName.value!!.trim()
                            val email = inputEmail.value!!.trim()
                            val password = inputPassword.value!!.trim()
                            auth.createUserWithEmailAndPassword(email,password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {

                                        val atIndex = email.indexOf("@")
                                        val email_without_end = email.substring(0, atIndex)
                                        val userName = businessName + "_" + email_without_end


                                        sharedPreferencesManager.saveUserLoggedIn(true)
                                        sharedPreferencesManager.saveUserName(userName)
                                        sharedPreferencesManager.saveInvoiceNumber(0)

                                        addUserToFireStore(
                                            User(
                                                businessName,
                                                email,
                                                password,
                                                userName,
                                                0,
                                                0
                                            )
                                        )

                                        statusMessage.value = Event("User account created successfully.")
                                        _showProgressDialog.value = false
                                        _navigateToDashboard.value = true
                                    } else {
                                        _showProgressDialog.value = false
                                        statusMessage.value = Event(task.exception?.message.toString())
                                    }
                                }

                        }
                    }
                    .addOnFailureListener { exception ->
                        statusMessage.value = Event(exception.message.toString())
                        _showProgressDialog.value = false

                    }

            }

        }

    }

    private fun addUserToFireStore(user: User) {


        // Create a reference to the user's document
        val userDocRef = firestoreDB.collection(USERS_COLLECTION)
            .document(user.email)


        // Add the user data to the document
        userDocRef.set(user)
            .addOnSuccessListener {
                statusMessage.value = Event("User data added successfully.")
            }
            .addOnFailureListener { e ->
                statusMessage.value = Event(e.message.toString())

            }
    }

    fun resetNavigation() {
        _navigateToDashboard.value = false
    }
}