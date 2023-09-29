package com.prplmnstr.invoiceapp.model

data class Tax(
    val name:String,
    val rate:Double=0.0,
    val taxAmount:Double=0.0
)