package com.prplmnstr.invoiceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.prplmnstr.invoiceapp.data.database.AppDatabase
import com.prplmnstr.invoiceapp.databinding.ActivityMainBinding
import com.prplmnstr.invoiceapp.model.BusinessDetails
import com.prplmnstr.invoiceapp.model.Invoice
import com.prplmnstr.invoiceapp.repository.InvoiceRepository
import com.prplmnstr.invoiceapp.viewmodel.InvoiceViewModel
import com.prplmnstr.invoiceapp.viewmodel.InvoiceViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var invoiceViewModel: InvoiceViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dao = AppDatabase.getInstance(application).invoiceDao
        val repository =InvoiceRepository(dao)
        val factory = InvoiceViewModelFactory(repository)
        invoiceViewModel = ViewModelProvider(this,factory)[InvoiceViewModel::class.java]


        invoiceViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
        val businessDetails = BusinessDetails("logo","name",
            "email","phone","address","web","gst")

        val invoice = Invoice("6",businessDetails,"fsf"
            ,"fdg","f","fdsf","g"
            ,"fs")
        invoiceViewModel.insert(invoice)
    }
}