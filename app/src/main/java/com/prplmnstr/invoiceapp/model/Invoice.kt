package com.prplmnstr.invoiceapp.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prplmnstr.invoiceapp.utils.Constants


@Entity(tableName =Constants.INVOICE_TABLE)
data class Invoice(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "invoice_number")
    val invoiceNumber: String,
    @ColumnInfo(name = "business_details")
    val businessDetails: BusinessDetails,
    @ColumnInfo(name = "client")
    val client: Client,
    @ColumnInfo(name = "items")
    val items: List<Item>,
    @ColumnInfo(name = "invoice_details")
    val invoiceDetails: InvoiceDetails,
    @ColumnInfo(name = "sign")
    val signatureImageUrl: String?,
    @ColumnInfo(name = "discount")
    val overallDiscount: Discount?,
    @ColumnInfo(name = "tax")
    val tax: Tax?
)