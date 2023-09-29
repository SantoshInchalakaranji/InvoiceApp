package com.prplmnstr.invoiceapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.prplmnstr.invoiceapp.model.Invoice
import com.prplmnstr.invoiceapp.utils.InvoiceTypeConverter

@Database(entities = [Invoice::class],version = 1)
@TypeConverters(InvoiceTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val invoiceDao : InvoiceDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null
        fun getInstance(context: Context):AppDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "invoice_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
