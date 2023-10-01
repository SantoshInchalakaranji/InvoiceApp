package com.prplmnstr.invoiceapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.prplmnstr.invoiceapp.databinding.FragmentInvoiceBinding
import com.prplmnstr.invoiceapp.databinding.FragmentQuotationBinding


class QuotationFragment : Fragment() {
    private lateinit var binding: FragmentQuotationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuotationBinding.inflate(inflater, container, false)
        val customToolbar = binding.customToolbar

        // Set the custom toolbar as the action bar
        (requireActivity() as AppCompatActivity).setSupportActionBar(customToolbar)

        // Enable the Up button (optional)

        (requireActivity() as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        return binding.root
    }


}