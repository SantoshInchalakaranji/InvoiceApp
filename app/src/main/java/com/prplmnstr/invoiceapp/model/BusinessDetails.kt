package com.prplmnstr.invoiceapp.model

data class BusinessDetails(
    val logoUrl: String?,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val website: String?,
    val gstNo: String
)