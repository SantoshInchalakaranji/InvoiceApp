package com.prplmnstr.invoiceapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.prplmnstr.invoiceapp.databinding.FragmentBusinessInfoBinding
import com.prplmnstr.invoiceapp.databinding.FragmentNewInvoiceBinding
import com.prplmnstr.invoiceapp.viewmodel.InvoiceViewModel


class BussinessInfoFragment : Fragment() {

    private lateinit var binding: FragmentBusinessInfoBinding
    private  val invoiceViewModel: InvoiceViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBusinessInfoBinding.inflate(inflater, container, false)
        val customToolbar = binding.customToolbar

        // Disable the default action bar
        //(requireActivity() as AppCompatActivity).supportActionBar?.hide()

        // Set the custom toolbar as the action bar
        (requireActivity() as AppCompatActivity).setSupportActionBar(customToolbar)

        // Enable the Up button (optional)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        binding.viewModel = invoiceViewModel

        // Set the lifecycle owner
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

    }

}