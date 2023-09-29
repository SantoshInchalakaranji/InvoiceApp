package com.prplmnstr.invoiceapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.prplmnstr.invoiceapp.model.Invoice


@Dao
interface InvoiceDao {

    @Insert
    suspend fun insertInvoice(invoice: Invoice) : Long

    @Update
    suspend fun updateInvoice(invoice: Invoice) : Int

    @Delete
    suspend fun deleteInvoice(invoice: Invoice) : Int


}