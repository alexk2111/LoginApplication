package com.onix.okucherenko.loginapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.onix.okucherenko.loginapplication.databinding.FragmentSplashBinding
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rootFrame.setOnClickListener {
            val snackBar = Snackbar
                .make(binding.root, getString(R.string.splash_snack_massage), Snackbar.LENGTH_SHORT)
                .setAnchorView(binding.animationView)
                snackBar.view.setBackgroundColor(resources.getColor(R.color.teal_700, null))
            snackBar.show()

        }

        val lottieAnimationPicture1 = R.raw.loading09
        val lottieAnimationPicture2 = R.raw.loading041

        val lottie = binding.animationView
        lottie.setAnimation(lottieAnimationPicture1)
        lottie.setOnClickListener {
            lottie.setAnimation(lottieAnimationPicture2)
            lottie.playAnimation()
        }

        val currentNavController = findNavController()
        GlobalScope.launch(Dispatchers.Main) {
            delay(5000)
            currentNavController.navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}