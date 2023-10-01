package com.prplmnstr.invoiceapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prplmnstr.invoiceapp.utils.Constants

@Entity(tableName = Constants.INVOICE_TABLE)
data class Item(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int?=0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "price")
    val price: Double=0.0,
    @ColumnInfo(name = "quantity")
    val quantity: Double=0.0,
    @ColumnInfo(name = "unit_of_measure")
    val unitOfMeasure: String?=null,
    @ColumnInfo(name = "discount")
    val discount: Double?=0.0,
    @ColumnInfo(name = "tax_rate")
    val taxRate: Double?=0.0,
    @ColumnInfo(name = "total_amount")
    val totalAmount:Double? = 0.0
)