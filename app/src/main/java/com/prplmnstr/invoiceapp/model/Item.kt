package com.prplmnstr.invoiceapp.model

data class Item(
    val name: String,
    val price: Double,
    val quantity: Double,
    val unitOfMeasure: String,
    val discount: Double,
    val taxRate: Double,
    val totalAmount:Double
)