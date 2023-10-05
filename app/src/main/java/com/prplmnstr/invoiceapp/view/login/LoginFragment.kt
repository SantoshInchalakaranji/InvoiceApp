package com.prplmnstr.invoiceapp.view.login

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.prplmnstr.invoiceapp.R
import com.prplmnstr.invoiceapp.databinding.FragmentLoginBinding
import com.prplmnstr.invoiceapp.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private  val loginViewModel: LoginViewModel by activityViewModels()
    private lateinit var progressDialog : AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = loginViewModel

        // Set the lifecycle owner
        binding.lifecycleOwner = this

        binding.signupTv.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        binding.arrow.setOnClickListener {
            findNavController().navigateUp()
        }

        loginViewModel.message.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
            }
        })

        loginViewModel.navigateToDashboard.observe(viewLifecycleOwner, Observer { shouldNavigate ->
            if (shouldNavigate) {


                findNavController().navigate(R.id.action_loginFragment_to_invoiceFragment)
                // Reset the LiveData to prevent repeated navigation
                loginViewModel.resetNavigation()
            }
        })

        loginViewModel.showProgressDialog.observe(viewLifecycleOwner, Observer { showProgress ->
            if (showProgress) {
                // Show the progress dialog
                showProgressDialog("Logging in...")
            } else {
                // Dismiss the progress dialog
                dismissProgressDialog()
            }
        })

        progressDialog = AlertDialog.Builder(requireContext()).create()
        val inflater = LayoutInflater.from(requireContext())
        val dialogView = inflater.inflate(R.layout.progress_dialog, null)
        progressDialog.setView(dialogView)
        progressDialog.setCancelable(false)
    }
    private fun showProgressDialog(message: String) {
        progressDialog = AlertDialog.Builder(requireContext()).create()
        val inflater = LayoutInflater.from(requireContext())
        val dialogView = inflater.inflate(R.layout.progress_dialog, null)
        val messageTextView = dialogView.findViewById<TextView>(R.id.message_text)
        messageTextView.text = message
        progressDialog.setView(dialogView)
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    private fun dismissProgressDialog() {

        progressDialog?.dismiss()
    }
}