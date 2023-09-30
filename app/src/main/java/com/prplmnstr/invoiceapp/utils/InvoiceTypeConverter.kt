package com.prplmnstr.invoiceapp.utils

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.prplmnstr.invoiceapp.model.BusinessDetails
import com.prplmnstr.invoiceapp.model.Client
import com.prplmnstr.invoiceapp.model.Discount
import com.prplmnstr.invoiceapp.model.InvoiceDetails
import com.prplmnstr.invoiceapp.model.Item
import com.prplmnstr.invoiceapp.model.Tax

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

    @TypeConverter
    fun fromInvoiceDetails(invoiceDetails:InvoiceDetails ): String {
        // Convert BusinessDetails to JSON or another suitable format
        return Gson().toJson(invoiceDetails)
    }

    @TypeConverter
    fun toInvoiceDetails(json: String): InvoiceDetails {
        // Convert the stored JSON back to BusinessDetails
        return Gson().fromJson(json, InvoiceDetails::class.java)
    }

    @TypeConverter
    fun fromClient(client: Client): String {
        // Convert BusinessDetails to JSON or another suitable format
        return Gson().toJson(client)
    }

    @TypeConverter
    fun toClient(json: String): Client {
        // Convert the stored JSON back to BusinessDetails
        return Gson().fromJson(json, Client::class.java)
    }

    @TypeConverter
    fun fromDiscount(discount: Discount): String {
        // Convert BusinessDetails to JSON or another suitable format
        return Gson().toJson(discount)
    }

    @TypeConverter
    fun toDiscount(json: String): Discount {
        // Convert the stored JSON back to BusinessDetails
        return Gson().fromJson(json, Discount::class.java)
    }

    @TypeConverter
    fun fromItemList(itemList: List<Item>): String {
        // Convert BusinessDetails to JSON or another suitable format
        return Gson().toJson(itemList)
    }

    @TypeConverter
    fun toItemList(json: String): List<Item> {
        val itemType = object : TypeToken<List<Item>>() {}.type
        return Gson().fromJson(json, itemType)
    }

    @TypeConverter
    fun fromTax(tax: Tax): String {
        // Convert BusinessDetails to JSON or another suitable format
        return Gson().toJson(tax)
    }

    @TypeConverter
    fun toTax(json: String): Tax {
        // Convert the stored JSON back to BusinessDetails
        return Gson().fromJson(json, Tax::class.java)
    }


}