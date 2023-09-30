package com.prplmnstr.invoiceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.prplmnstr.invoiceapp.model.BusinessDetails
import com.prplmnstr.invoiceapp.model.Client
import com.prplmnstr.invoiceapp.model.Discount
import com.prplmnstr.invoiceapp.model.DiscountType
import com.prplmnstr.invoiceapp.model.Invoice
import com.prplmnstr.invoiceapp.model.InvoiceDetails
import com.prplmnstr.invoiceapp.model.Item
import com.prplmnstr.invoiceapp.model.Tax
import com.prplmnstr.invoiceapp.pdf_templetes.Templete1

class TempleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_templete)

        val templete1 =  Templete1()

        val businessDetails = BusinessDetails("logo","name",
            "email","phone","address","web","gst")

        var invoiceDetails = InvoiceDetails("INVOICE",
            "inv00001","12/03/2023",
            "22/12/2023","ENGLISH",
            1,
            "terms and condition",
            "INR",
            "COD",
            1000.0,
            200.0,200.0)

        var client = Client(
            "fresh mart","emailclient","123456789","billing address",
            "gst","shipping address"
        )
        var discount = Discount(DiscountType.FLAT_AMOUNT,1000.0,1000.0)
        var tax = Tax("IGST",12.0,1000.0)
        var sign = "sign"
        var itemList:MutableList<Item> = mutableListOf()
        var item1 = Item(
            "itemName",123.0,
            1.0,"",12.0,5.0,1000.0)
        itemList.add(item1)

        val invoice = Invoice("17",
            businessDetails,client,itemList,invoiceDetails,sign,discount,tax)

      //  templete1.createPdf(this,this,invoice)
    }
}