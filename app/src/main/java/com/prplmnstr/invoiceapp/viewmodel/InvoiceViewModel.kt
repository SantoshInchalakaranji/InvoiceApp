package com.prplmnstr.invoiceapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prplmnstr.invoiceapp.data.database.firebase.FirebaseResult
import com.prplmnstr.invoiceapp.model.Invoice
import com.prplmnstr.invoiceapp.repository.InvoiceRepository
import com.prplmnstr.invoiceapp.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InvoiceViewModel(private val repository: InvoiceRepository) : ViewModel(){


    private val statusMessage = MutableLiveData<Event<String>>()

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