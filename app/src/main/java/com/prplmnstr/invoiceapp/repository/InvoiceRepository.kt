package com.prplmnstr.invoiceapp.repository

import com.prplmnstr.invoiceapp.data.database.InvoiceDao
import com.prplmnstr.invoiceapp.model.Invoice

class InvoiceRepository(private val dao : InvoiceDao) {



    suspend fun insert(invoice: Invoice) : Long{
        return dao.insertInvoice(invoice)
    }

    suspend fun update(invoice: Invoice) : Int{
        return dao.updateInvoice(invoice)
    }

//    suspend fun delete(subscriber: Subscriber) : Int{
//        return dao.deleteSubscriber(subscriber)
//    }
//
//    suspend fun deleteAll() : Int{
//        return dao.deleteAll()
//    }

}