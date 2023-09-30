package com.prplmnstr.invoiceapp.repository

import com.prplmnstr.invoiceapp.data.database.firebase.FirebaseDataSource
import com.prplmnstr.invoiceapp.data.database.firebase.FirebaseResult
import com.prplmnstr.invoiceapp.data.database.room.InvoiceDao
import com.prplmnstr.invoiceapp.model.Invoice

class InvoiceRepository(private val dao : InvoiceDao ,private val firebaseDataSource: FirebaseDataSource) {



    suspend fun insert(invoice: Invoice) : Long{
        return dao.insertInvoice(invoice)
    }

    suspend fun update(invoice: Invoice) : Int{
        return dao.updateInvoice(invoice)
    }

    suspend fun delete(invoice: Invoice) : Int{
        return dao.deleteInvoice(invoice)
    }

    // New methods for Firebase operations

    suspend fun addInvoiceToFirebase(invoice: Invoice):FirebaseResult {
        return  firebaseDataSource.addInvoiceToFirebase(invoice)

    }

    suspend fun updateInvoiceInFirebase(invoice: Invoice):FirebaseResult {
        return  firebaseDataSource.updateInvoiceInFirebase(invoice)

    }

}