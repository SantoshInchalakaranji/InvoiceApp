package com.prplmnstr.invoiceapp.utils

class Constants {
    companion object{

        //Room database
        const val  DATABASE_NAME = "invoice_database"
        const val  INVOICE_TABLE = "invoice"
        const val  CLIENT_TABLE = "client"
        const val  ITEM_TABLE = "item"

        // Firestore
        const val INVOICE_COLLECTION = "Invoices"
        const val USERS_COLLECTION = "Users"

    }
}