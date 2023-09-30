package com.prplmnstr.invoiceapp.data.database.firebase

sealed class FirebaseResult {
    data class Success(val message: String) : FirebaseResult()
    data class Failure(val error: Exception) : FirebaseResult()
}