package com.onix.okucherenko.loginapplication.ui.result

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.onix.okucherenko.loginapplication.R
import com.onix.okucherenko.loginapplication.databinding.FragmentResultBinding

class ResultFragment : Fragment(R.layout.fragment_result) {

    private var fragmentResultBinding: FragmentResultBinding? = null

    private lateinit var viewModel: ResultViewModel
    private lateinit var viewModelFactory: ResultViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentResultBinding.bind(view)
        fragmentResultBinding = binding

        viewModelFactory =
            ResultViewModelFactory(ResultFragmentArgs.fromBundle(requireArguments()).modelQuiz)
        viewModel = ViewModelProvider(this, viewModelFactory)[ResultViewModel::class.java]

        binding.textViewResult.text = viewModel.resultTest
    }
}