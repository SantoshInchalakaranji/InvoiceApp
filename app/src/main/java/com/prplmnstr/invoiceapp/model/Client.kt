package com.prplmnstr.invoiceapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prplmnstr.invoiceapp.utils.Constants

@Entity(tableName = Constants.CLIENT_TABLE)
data class Client(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int? =0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "billing_address")
    val billingAddress: String,
    @ColumnInfo(name = "gstno")
    val gstNo: String?=null,
    @ColumnInfo(name = "shipping_address")
    val shippingAddress: String
)