package com.prplmnstr.invoiceapp.model

data class Client(
    val name: String,
    val email: String,
    val phone: String,
    val billingAddress: String,
    val gstNo: String,
    val shippingAddress: String
)