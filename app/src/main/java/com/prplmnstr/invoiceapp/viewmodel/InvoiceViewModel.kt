package com.prplmnstr.invoiceapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                statusMessage.value = Event("Subscriber Inserted Successfully! $newRowId")
            }else{
                statusMessage.value = Event("Error Occurred!")
            }
        }
    }
}