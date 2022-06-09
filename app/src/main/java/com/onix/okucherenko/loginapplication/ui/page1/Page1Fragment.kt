package com.onix.okucherenko.loginapplication.ui.page1

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.onix.okucherenko.loginapplication.R
import com.onix.okucherenko.loginapplication.databinding.FragmentPage1Binding

class Page1Fragment : Fragment(R.layout.fragment_page1) {

    private var fragmentPage1Binding: FragmentPage1Binding? = null

    private lateinit var viewModel: Page1ViewModel
    private lateinit var viewModelFactory: Page1ViewModelFactory
    lateinit var binding: FragmentPage1Binding
    var actualPage = 0

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

        binding.editTextTextName.setOnFocusChangeListener { _: View, hasFocus ->
            if (!hasFocus) {
//                viewModel.quiz.page[actualPage].question[0].answers[0].result =
//                    binding.editTextTextName.text.toString().trim()
            }
        }
        binding.editTextTextSurName.setOnFocusChangeListener { _: View, hasFocus ->
            if (!hasFocus) {
//                viewModel.quiz.page[actualPage].question[0].answers[1].result =
//                    binding.editTextTextSurName.text.toString().trim()
            }
        }
        binding.editTextAge.setOnFocusChangeListener { _: View, hasFocus ->
            if (!hasFocus) {
//                viewModel.quiz.page[actualPage].question[0].answers[2].result =
//                    binding.editTextAge.text.toString()
            }
        }
        binding.buttonPage1toPage2.setOnClickListener {
            onClickButtonPage1toPage2()
        }
    }

    private fun onClickButtonPage1toPage2() {

        if (binding.editTextTextName.text.isEmpty() ||
            binding.editTextTextSurName.text.isEmpty() ||
            binding.editTextAge.text.isEmpty()
        ) {
            Toast.makeText(context, "All fields must be completed!!!", Toast.LENGTH_SHORT).show()
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