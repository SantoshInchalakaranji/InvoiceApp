package com.prplmnstr.invoiceapp

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.prplmnstr.invoiceapp.data.database.firebase.FirebaseDataSource
import com.prplmnstr.invoiceapp.data.database.room.AppDatabase
import com.prplmnstr.invoiceapp.databinding.FragmentInvoiceBinding
import com.prplmnstr.invoiceapp.repository.InvoiceRepository
import com.prplmnstr.invoiceapp.viewmodel.InvoiceViewModel
import com.prplmnstr.invoiceapp.viewmodel.InvoiceViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class InvoiceFragment : Fragment() {


    private lateinit var binding: FragmentInvoiceBinding
    private lateinit var moveUpDownAnimation: Animation
    private  val invoiceViewModel: InvoiceViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentInvoiceBinding.inflate(inflater, container, false)
        val customToolbar = binding.customToolbar

        // Set the custom toolbar as the action bar
        (requireActivity() as AppCompatActivity).setSupportActionBar(customToolbar)

        // Enable the Up button (optional)

        (requireActivity() as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        //animate comment text view
        NewAddButtonAnimation()

        binding.floatingActionButton.setOnClickListener {
            // Navigate to the NewInvoiceFragment
            findNavController().navigate(R.id.action_invoiceFragment_to_newInvoiceFragment)
        }

        binding.commentTextView.setOnClickListener {
            // Navigate to the NewInvoiceFragment
            findNavController().navigate(R.id.action_invoiceFragment_to_newInvoiceFragment)
        }


    }

    private fun NewAddButtonAnimation() {
        moveUpDownAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.move_up_down)

        val textView = binding.commentTextView

        // Create ObjectAnimator to move the TextView up
        val moveUp = ObjectAnimator.ofFloat(textView, "translationY", 0f, -100f)
        moveUp.duration = 1000 // Adjust duration as needed

        // Create ObjectAnimator to move the TextView down
        val moveDown = ObjectAnimator.ofFloat(textView, "translationY", -100f, 0f)
        moveDown.duration = 1000 // Adjust duration as needed


        // Create a ValueAnimator to smoothly move the TextView up and down
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.duration = 1000 // Total duration for the full animation
        animator.interpolator = LinearInterpolator()
        animator.repeatMode = ValueAnimator.REVERSE
        animator.repeatCount = ValueAnimator.INFINITE

        animator.addUpdateListener { animation ->
            val progress = animation.animatedValue as Float
            // Adjust the translationY based on the progress
            val translationY = -50f * progress // Adjust the distance as needed
            textView.translationY = translationY
        }

        animator.start()
    }
}
