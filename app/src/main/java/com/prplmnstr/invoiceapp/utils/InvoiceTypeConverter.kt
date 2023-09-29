package com.prplmnstr.invoiceapp.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.prplmnstr.invoiceapp.model.BusinessDetails

class InvoiceTypeConverter {
    @TypeConverter
    fun fromBusinessDetails(businessDetails: BusinessDetails): String {
        // Convert BusinessDetails to JSON or another suitable format
        return Gson().toJson(businessDetails)
    }

    @TypeConverter
    fun toBusinessDetails(json: String): BusinessDetails {
        // Convert the stored JSON back to BusinessDetails
        return Gson().fromJson(json, BusinessDetails::class.java)
    }

}