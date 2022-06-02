package com.onix.okucherenko.loginapplication.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.onix.okucherenko.loginapplication.R
import com.onix.okucherenko.loginapplication.databinding.FragmentResultBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {
    val args: ResultFragmentArgs by navArgs()

    //binding
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ResultViewModel
    private lateinit var viewModelFactory: ResultViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModelFactory = ResultViewModelFactory(ResultFragmentArgs.fromBundle(requireArguments()).userName)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ResultViewModel::class.java)
        binding.userName.text = "${getString(R.string.welcome)} ${viewModel.userName}"

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}