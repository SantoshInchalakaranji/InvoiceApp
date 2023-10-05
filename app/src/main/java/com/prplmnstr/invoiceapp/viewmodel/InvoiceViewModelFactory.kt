package com.prplmnstr.invoiceapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prplmnstr.invoiceapp.repository.InvoiceRepository
import java.lang.IllegalArgumentException

class InvoiceViewModelFactory(private val repository: InvoiceRepository,private val application:Application): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(InvoiceViewModel::class.java)) {
            return InvoiceViewModel(repository,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}