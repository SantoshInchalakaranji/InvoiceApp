package com.prplmnstr.invoiceapp.model

data class BusinessDetails(
    var logoUrl: String?,
    var name: String,
    var email: String,
    var phone: String,
    var address: String,
    var website: String?,
    var gstNo: String
)