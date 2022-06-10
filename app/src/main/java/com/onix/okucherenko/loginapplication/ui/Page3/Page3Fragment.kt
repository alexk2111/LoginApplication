package com.onix.okucherenko.loginapplication.ui.Page3

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.onix.okucherenko.loginapplication.R
import com.onix.okucherenko.loginapplication.databinding.FragmentPage3Binding

class Page3Fragment : Fragment(R.layout.fragment_page3) {

    private var fragmentPage3Binding: FragmentPage3Binding? = null

    private lateinit var viewModel: Page3ViewModel
    private lateinit var viewModelFactory: Page3ViewModelFactory
    private lateinit var binding: FragmentPage3Binding
    private var actualPage = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPage3Binding.bind(view)
        fragmentPage3Binding = binding

        viewModelFactory =
            Page3ViewModelFactory(Page3FragmentArgs.fromBundle(requireArguments()).modelQuiz)
        viewModel = ViewModelProvider(this, viewModelFactory)[Page3ViewModel::class.java]

        actualPage = viewModel.actualPage

        binding.textViewContext.text = viewModel.quiz.page[actualPage].question[0].content

        binding.checkBoxAnswer1.text = viewModel.quiz.page[actualPage].question[0].answers[0].content
        binding.checkBoxAnswer2.text = viewModel.quiz.page[actualPage].question[0].answers[1].content
        binding.checkBoxAnswer3.text = viewModel.quiz.page[actualPage].question[0].answers[2].content
        binding.checkBoxAnswer4.text = viewModel.quiz.page[actualPage].question[0].answers[3].content
        binding.checkBoxAnswer5.text = viewModel.quiz.page[actualPage].question[0].answers[4].content
        binding.checkBoxAnswer6.text = viewModel.quiz.page[actualPage].question[0].answers[5].content
        binding.checkBoxAnswer7.text = viewModel.quiz.page[actualPage].question[0].answers[6].content
        binding.checkBoxAnswer8.text = viewModel.quiz.page[actualPage].question[0].answers[7].content
        binding.checkBoxAnswer9.text = viewModel.quiz.page[actualPage].question[0].answers[8].content
        binding.checkBoxAnswer10.text = viewModel.quiz.page[actualPage].question[0].answers[9].content

        binding.buttonPage3toResult.setOnClickListener {
            onClickButtonPage3toResult()
        }
    }


    private fun onClickButtonPage3toResult() {
        if (!(binding.checkBoxAnswer1.isChecked ||
            binding.checkBoxAnswer2.isChecked ||
            binding.checkBoxAnswer3.isChecked ||
            binding.checkBoxAnswer4.isChecked ||
            binding.checkBoxAnswer5.isChecked ||
            binding.checkBoxAnswer6.isChecked ||
            binding.checkBoxAnswer7.isChecked ||
            binding.checkBoxAnswer8.isChecked ||
            binding.checkBoxAnswer9.isChecked ||
            binding.checkBoxAnswer10.isChecked)){
            val snackBar = Snackbar
                .make(binding.root, "Make a choice!!!", Snackbar.LENGTH_SHORT)
            snackBar.view.setBackgroundColor(Color.RED)
            snackBar.show()
            return
        }

        if(binding.checkBoxAnswer1.isChecked) {
            viewModel.quiz.page[actualPage].question[0].answers[0].result =
                binding.checkBoxAnswer1.text.toString()
        } else {
            viewModel.quiz.page[actualPage].question[0].answers[0].result = ""
        }
        if(binding.checkBoxAnswer2.isChecked) {
            viewModel.quiz.page[actualPage].question[0].answers[1].result =
                binding.checkBoxAnswer2.text.toString()
        } else {
            viewModel.quiz.page[actualPage].question[0].answers[1].result = ""
        }
        if(binding.checkBoxAnswer3.isChecked) {
            viewModel.quiz.page[actualPage].question[0].answers[2].result =
                binding.checkBoxAnswer3.text.toString()
        } else {
            viewModel.quiz.page[actualPage].question[0].answers[2].result = ""
        }
        if(binding.checkBoxAnswer4.isChecked) {
            viewModel.quiz.page[actualPage].question[0].answers[3].result =
                binding.checkBoxAnswer4.text.toString()
        } else {
            viewModel.quiz.page[actualPage].question[0].answers[3].result = ""
        }
        if(binding.checkBoxAnswer5.isChecked) {
            viewModel.quiz.page[actualPage].question[0].answers[4].result =
                binding.checkBoxAnswer5.text.toString()
        } else {
            viewModel.quiz.page[actualPage].question[0].answers[4].result = ""
        }
        if(binding.checkBoxAnswer6.isChecked) {
            viewModel.quiz.page[actualPage].question[0].answers[5].result =
                binding.checkBoxAnswer6.text.toString()
        } else {
            viewModel.quiz.page[actualPage].question[0].answers[5].result = ""
        }
        if(binding.checkBoxAnswer7.isChecked) {
            viewModel.quiz.page[actualPage].question[0].answers[6].result =
                binding.checkBoxAnswer7.text.toString()
        } else {
            viewModel.quiz.page[actualPage].question[0].answers[6].result = ""
        }
        if(binding.checkBoxAnswer8.isChecked) {
            viewModel.quiz.page[actualPage].question[0].answers[7].result =
                binding.checkBoxAnswer8.text.toString()
        } else {
            viewModel.quiz.page[actualPage].question[0].answers[7].result = ""
        }
        if(binding.checkBoxAnswer9.isChecked) {
            viewModel.quiz.page[actualPage].question[0].answers[8].result =
                binding.checkBoxAnswer9.text.toString()
        } else {
            viewModel.quiz.page[actualPage].question[0].answers[8].result = ""
        }
        if(binding.checkBoxAnswer10.isChecked) {
            viewModel.quiz.page[actualPage].question[0].answers[9].result =
                binding.checkBoxAnswer10.text.toString()
        } else {
            viewModel.quiz.page[actualPage].question[0].answers[9].result = ""
        }

        viewModel.onClickButtonPage3toResult()
        val amount = viewModel.modelQuiz
        val action = Page3FragmentDirections.actionPage3FragmentToResultFragment(amount)
        NavHostFragment.findNavController(this).navigate(action)
    }


    override fun onDestroy() {
        super.onDestroy()
        fragmentPage3Binding = null
    }
}