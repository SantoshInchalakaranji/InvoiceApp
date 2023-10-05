package com.prplmnstr.invoiceapp.viewmodel

import SharedPreferencesManager
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prplmnstr.invoiceapp.data.database.firebase.FirebaseResult
import com.prplmnstr.invoiceapp.model.BusinessDetails
import com.prplmnstr.invoiceapp.model.Client
import com.prplmnstr.invoiceapp.model.Discount
import com.prplmnstr.invoiceapp.model.DiscountType
import com.prplmnstr.invoiceapp.model.Invoice
import com.prplmnstr.invoiceapp.model.InvoiceDetails
import com.prplmnstr.invoiceapp.model.Tax
import com.prplmnstr.invoiceapp.repository.InvoiceRepository
import com.prplmnstr.invoiceapp.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InvoiceViewModel(private val repository: InvoiceRepository,private val application: Application) : ViewModel(){


    private val statusMessage = MutableLiveData<Event<String>>()
    val invoiceObject=MutableLiveData<Invoice>()
    val sharedPreferencesManager = SharedPreferencesManager(application)


    init {
       val invoiceNumber = sharedPreferencesManager.getInvoiceNumber()
        val sampleInvoice = Invoice(
          businessDetails = BusinessDetails("","",
              "","","","",""),
            client = Client(
                null,"","","","",
                "",""
            ),
            invoiceNumber = "INV"+invoiceNumber,
            overallDiscount = Discount(DiscountType.FLAT_AMOUNT,1000.0,1000.0),
            tax = Tax("IGST",12.0,1000.0),
            items = emptyList(),
            invoiceDetails = InvoiceDetails("INVOICE",
                "INV"+invoiceNumber,"12/03/2023",
                "22/12/2023","ENGLISH",
                1,
                "terms and condition",
                "INR",
                "COD",
                1000.0,
                200.0,200.0),
            signatureImageUrl = ""



        )
        invoiceObject.value = sampleInvoice
    }
    val message : LiveData<Event<String>>
        get() = statusMessage


     fun insert(invoice: Invoice) = viewModelScope.launch(Dispatchers.IO) {
        val newRowId = repository.insert(invoice)
        withContext(Dispatchers.Main){
            if(newRowId > -1) {
                statusMessage.value = Event("Invoice Inserted Successfully! $newRowId")
            }else{
                statusMessage.value = Event("Error Occurred!")
            }
        }
    }

    fun getInvoiceNumber(){

        statusMessage.value = Event(sharedPreferencesManager.getInvoiceNumber().toString())
    }

    // New method to add invoice to Firebase
     fun addInvoiceToFirebase(invoice: Invoice) {
        viewModelScope.launch(Dispatchers.IO) {
            val firebaseResult = repository.addInvoiceToFirebase(invoice)
            withContext(Dispatchers.Main) {
                if (firebaseResult is FirebaseResult.Success) {
                    statusMessage.value = Event("Invoice added successfully!")
                } else {
                    statusMessage.value = Event("Error adding invoice to Firebase!")
                }
            }
        }
    }

    // New method to update invoice in Firebase
    fun updateInvoiceInFirebase(invoice: Invoice) {
        viewModelScope.launch(Dispatchers.IO) {
            val firebaseResult = repository.updateInvoiceInFirebase(invoice)
            withContext(Dispatchers.Main) {
                if (firebaseResult is FirebaseResult.Success) {
                    statusMessage.value = Event("Invoice updated successfully!")
                } else {
                    statusMessage.value = Event("Error updating invoice in Firebase!")
                }
            }
        }
    }
}