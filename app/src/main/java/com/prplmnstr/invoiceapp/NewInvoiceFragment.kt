package com.prplmnstr.invoiceapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.prplmnstr.invoiceapp.databinding.FragmentInvoiceBinding
import com.prplmnstr.invoiceapp.databinding.FragmentNewInvoiceBinding
import com.prplmnstr.invoiceapp.viewmodel.InvoiceViewModel


class NewInvoiceFragment : Fragment() {

    private lateinit var binding: FragmentNewInvoiceBinding
    private  val invoiceViewModel: InvoiceViewModel by activityViewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewInvoiceBinding.inflate(inflater, container, false)
        val customToolbar = binding.customToolbar
        // Set the custom toolbar as the action bar
        (requireActivity() as AppCompatActivity).setSupportActionBar(customToolbar)

        // Enable the Up button (optional)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.viewModel = invoiceViewModel

        // Set the lifecycle owner
        binding.lifecycleOwner = this



//        val bottomNavView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//
//        // Set its visibility to GONE to hide it
//        bottomNavView.visibility = View.GONE

        binding.signatureLayout.setOnClickListener {
            // Navigate to the NewInvoiceFragment
            findNavController().navigate(R.id.action_newInvoiceFragment_to_signFragment)
        }

        binding.fromLayout.setOnClickListener {
            // Navigate to the NewInvoiceFragment
            findNavController().navigate(R.id.action_newInvoiceFragment_to_bussinessInfoFragment)
        }
        binding.invoiceInfoCard.setOnClickListener {
            // Navigate to the NewInvoiceFragment
            findNavController().navigate(R.id.action_newInvoiceFragment_to_invoiceInfoFragment)
        }
    }
}