package com.prplmnstr.invoiceapp.model

data class InvoiceDetails(
    val title: String,
    val number: String,
    val creationDate: String,
    val dueDate: String,
    val language: String,
    val template: Int,
    val termsAndConditions: String,
    val currency: String,
    val paymentMethod: String,
    val totalAmount:Double=0.0,
    val subTotalAmount:Double =0.0,
    val shippingAmount:Double=0.0
)