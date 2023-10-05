package com.prplmnstr.invoiceapp.model

data class User(
    var businessName:String="",
    var email:String,
    var password:String,
    var userName:String,
    var invoiceNumber:Long=0,
    var quotationNumber:Long=0

)
