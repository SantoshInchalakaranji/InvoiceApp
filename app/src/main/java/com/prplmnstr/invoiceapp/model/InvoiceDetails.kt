package com.prplmnstr.invoiceapp.model

data class InvoiceDetails(
    var title: String,
    var number: String,
    var creationDate: String,
    var dueDate: String,
    var language: String,
    var template: Int,
    var termsAndConditions: String,
    var currency: String,
    var paymentMethod: String,
    var totalAmount:Double=0.0,
    var subTotalAmount:Double =0.0,
    var shippingAmount:Double=0.0
)