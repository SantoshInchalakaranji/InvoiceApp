package com.prplmnstr.invoiceapp.data.database.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.prplmnstr.invoiceapp.model.Client
import com.prplmnstr.invoiceapp.model.Invoice

@Dao
interface ClientDao {

    @Insert
    suspend fun insertClient(client: Client) : Long

    @Update
    suspend fun updateClient(client: Client) : Int

    @Delete
    suspend fun deleteClient(client: Client) : Int
}