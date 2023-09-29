package com.prplmnstr.invoiceapp.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName ="invoice")
data class Invoice(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "invoice_number")
    val invoiceNumber: String,
    @ColumnInfo(name = "business_details")
    val businessDetails: BusinessDetails,
    @ColumnInfo(name = "client")
    val client: String,
    @ColumnInfo(name = "items")
    val items: String,
    @ColumnInfo(name = "invoice_details")
    val invoiceDetails: String,
    @ColumnInfo(name = "sign")
    val signatureImageUrl: String?,
    @ColumnInfo(name = "discount")
    val overallDiscount:String?,
    @ColumnInfo(name = "tax")
    val tax: String?
)