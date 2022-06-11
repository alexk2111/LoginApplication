package com.onix.okucherenko.loginapplication.ui.page1

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.onix.okucherenko.loginapplication.R
import com.onix.okucherenko.loginapplication.databinding.FragmentPage1Binding

class Page1Fragment : Fragment(R.layout.fragment_page1) {

    private var fragmentPage1Binding: FragmentPage1Binding? = null

    private lateinit var viewModel: Page1ViewModel
    private lateinit var viewModelFactory: Page1ViewModelFactory
    private lateinit var binding: FragmentPage1Binding
    private var actualPage = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPage1Binding.bind(view)
        fragmentPage1Binding = binding

        viewModelFactory =
            Page1ViewModelFactory(Page1FragmentArgs.fromBundle(requireArguments()).modelQuiz)
        viewModel = ViewModelProvider(this, viewModelFactory)[Page1ViewModel::class.java]

        actualPage = viewModel.actualPage

        binding.textViewQuestionContent.text = viewModel.quiz.page[actualPage].question[0].content

        binding.textViewName.text = viewModel.quiz.page[actualPage].question[0].answers[0].content
        binding.textViewSurName.text =
            viewModel.quiz.page[actualPage].question[0].answers[1].content
        binding.textViewAge.text = viewModel.quiz.page[actualPage].question[0].answers[2].content

        binding.buttonPage1toPage2.setOnClickListener {
            onClickButtonPage1toPage2()
        }
    }

    private fun onClickButtonPage1toPage2() {

        if (binding.editTextTextName.text.isEmpty() ||
            binding.editTextTextSurName.text.isEmpty() ||
            binding.editTextAge.text.isEmpty()
        ) {
            val snackBar = Snackbar
                .make(binding.root, getString(R.string.error_must_be_completed), Snackbar.LENGTH_SHORT)
            snackBar.view.setBackgroundColor(Color.RED)
            snackBar.show()
            return
        }

        viewModel.quiz.page[actualPage].question[0].answers[0].result =
            binding.editTextTextName.text.toString().trim()
        viewModel.quiz.page[actualPage].question[0].answers[1].result =
            binding.editTextTextSurName.text.toString().trim()
        viewModel.quiz.page[actualPage].question[0].answers[2].result =
            binding.editTextAge.text.toString()

        viewModel.onClickButtonPage1toPage2()


        val amount = viewModel.modelQuiz
        val action = Page1FragmentDirections.actionPage1FragmentToPage2Fragment(amount)
        NavHostFragment.findNavController(this).navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentPage1Binding = null
    }
}