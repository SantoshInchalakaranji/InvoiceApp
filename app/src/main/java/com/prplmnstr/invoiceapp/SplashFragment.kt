package com.prplmnstr.invoiceapp

import SharedPreferencesManager
import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        val lottieAnimationView = view.findViewById<LottieAnimationView>(R.id.lottieAnimationView)

        // Set a listener to navigate to the next screen when the animation finishes
        lottieAnimationView.addAnimatorListener(object : Animator.AnimatorListener {

            override fun onAnimationStart(p0: Animator) {

            }

            override fun onAnimationEnd(p0: Animator) {
                val sharedPreferencesManager = SharedPreferencesManager(requireContext())
                val userLoggedIn = sharedPreferencesManager.isUserLoggedIn()
                val userName = sharedPreferencesManager.getUserName()

                if (userLoggedIn) {
                    // User is logged in, navigate to DashboardFragment
                    findNavController().navigate(R.id.action_splashFragment_to_invoiceFragment)

                } else {
                    // User is not logged in, navigate to LoginFragment
                    findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
                }

            }

            override fun onAnimationCancel(p0: Animator) {

            }

            override fun onAnimationRepeat(p0: Animator) {

            }
        })

        return view
    }


}