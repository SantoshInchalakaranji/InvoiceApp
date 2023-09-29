package com.prplmnstr.invoiceapp.model

data class Discount(
    val type: DiscountType,
    val value: Double=0.0,
    val discountAmount:Double=0.0
)

enum class DiscountType {
    PERCENTAGE,
    FLAT_AMOUNT
}