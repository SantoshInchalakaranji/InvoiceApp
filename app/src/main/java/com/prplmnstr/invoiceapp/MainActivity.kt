package com.prplmnstr.invoiceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.prplmnstr.invoiceapp.data.database.firebase.FirebaseDataSource
import com.prplmnstr.invoiceapp.data.database.room.AppDatabase
import com.prplmnstr.invoiceapp.databinding.ActivityMainBinding
import com.prplmnstr.invoiceapp.model.BusinessDetails
import com.prplmnstr.invoiceapp.model.Client
import com.prplmnstr.invoiceapp.model.Discount
import com.prplmnstr.invoiceapp.model.DiscountType
import com.prplmnstr.invoiceapp.model.Invoice
import com.prplmnstr.invoiceapp.model.InvoiceDetails
import com.prplmnstr.invoiceapp.model.Item
import com.prplmnstr.invoiceapp.model.Tax
import com.prplmnstr.invoiceapp.repository.InvoiceRepository
import com.prplmnstr.invoiceapp.viewmodel.InvoiceViewModel
import com.prplmnstr.invoiceapp.viewmodel.InvoiceViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var invoiceViewModel: InvoiceViewModel
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val dao = AppDatabase.getInstance(application).invoiceDao
        val firebaseDataSource = FirebaseDataSource()
        val repository =InvoiceRepository(dao,firebaseDataSource)
        val factory = InvoiceViewModelFactory(repository)
        invoiceViewModel = ViewModelProvider(this,factory)[InvoiceViewModel::class.java]




//        setSupportActionBar(binding.toolbar)
     navController = findNavController(R.id.navHostFragment)
//        val appBarConfiguration = AppBarConfiguration(setOf(
//            R.id.invoiceFragment,
//            R.id.quotationFragment,
//            R.id.clientFragment,
//            R.id.itemFragment
//        ))
        binding.bottomNavigationView.setupWithNavController(navController)
//        setupActionBarWithNavController(navController, appBarConfiguration)




        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.invoiceFragment -> {
                    // Hide the bottom navigation view when navigating to NewInvoiceFragment
                    binding.bottomNavigationView.visibility = View.VISIBLE

                }
                R.id.quotationFragment -> {
                    // Hide the bottom navigation view when navigating to NewInvoiceFragment
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.clientFragment -> {
                    // Hide the bottom navigation view when navigating to NewInvoiceFragment
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.itemFragment -> {
                    // Hide the bottom navigation view when navigating to NewInvoiceFragment
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                else -> {
                    // Show the bottom navigation view for other fragments
                    binding.bottomNavigationView.visibility = View.GONE
                }
            }
        }







        invoiceViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })





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
            null,"fresh mart","emailclient","123456789","billing address",
            "gst","shipping address"
        )
        var discount = Discount(DiscountType.FLAT_AMOUNT,1000.0,1000.0)
        var tax = Tax("IGST",12.0,1000.0)
        var sign = "sign"
        var itemList:MutableList<Item> = mutableListOf()
        var item1 =Item(
            null,"itemName",123.0,
        1.0,"",12.0,5.0,1000.0)
        itemList.add(item1)

        val invoice = Invoice("17",
        businessDetails,client,itemList,invoiceDetails,sign,discount,tax)
      //  invoiceViewModel.insert(invoice)
        //invoiceViewModel.addInvoiceToFirebase(invoice)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}