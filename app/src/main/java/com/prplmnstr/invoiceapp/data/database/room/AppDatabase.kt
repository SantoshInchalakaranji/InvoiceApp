package com.prplmnstr.invoiceapp.data.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.prplmnstr.invoiceapp.model.Client
import com.prplmnstr.invoiceapp.model.Invoice
import com.prplmnstr.invoiceapp.utils.Constants
import com.prplmnstr.invoiceapp.utils.InvoiceTypeConverter

@Database(entities = [Invoice::class,Client::class],version = 1)
@TypeConverters(InvoiceTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val invoiceDao : InvoiceDao
    abstract val clientDao : ClientDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        Constants.DATABASE_NAME
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
