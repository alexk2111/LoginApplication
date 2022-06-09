package com.onix.okucherenko.loginapplication.ui.Page3

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.onix.okucherenko.loginapplication.R
import com.onix.okucherenko.loginapplication.databinding.FragmentPage3Binding

class Page3Fragment : Fragment(R.layout.fragment_page3) {

    private var fragmentPage3Binding: FragmentPage3Binding? = null

    private lateinit var viewModel: Page3ViewModel
    private lateinit var viewModelFactory: Page3ViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentPage3Binding.bind(view)
        fragmentPage3Binding = binding

        viewModelFactory =
            Page3ViewModelFactory(Page3FragmentArgs.fromBundle(requireArguments()).modelQuiz)
        viewModel = ViewModelProvider(this, viewModelFactory)[Page3ViewModel::class.java]

        val actualPage = viewModel.actualPage

        binding.textView.text = viewModel.quiz.page[actualPage].question[0].content

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