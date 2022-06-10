package com.onix.okucherenko.loginapplication.ui.Page3

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textview.MaterialTextView
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

        updateViewText(binding.mainLayout, 0)

        binding.buttonPage3toResult.setOnClickListener {
            onClickButtonPage3toResult()
        }
    }

    private fun updateViewText(view: View, numberAnswer: Int): Int {
        var localNumberAnswer = numberAnswer
        if (view is ViewGroup) {
            view.children.forEach { localNumberAnswer = updateViewText(it, localNumberAnswer) }

        } else {
            when (view) {
                is MaterialTextView -> view.text =
                    viewModel.quiz.page[actualPage].question[0].content
                is MaterialCheckBox -> {
                    view.text =
                        viewModel.quiz.page[actualPage].question[0].answers[numberAnswer].content
                    localNumberAnswer++
                }
            }
        }
        return localNumberAnswer
    }

    private fun onClickButtonPage3toResult() {
        if (binding.checkBoxAnswer1.isChecked ||
            binding.checkBoxAnswer2.isChecked ||
            binding.checkBoxAnswer3.isChecked ||
            binding.checkBoxAnswer4.isChecked ||
            binding.checkBoxAnswer5.isChecked ||
            binding.checkBoxAnswer6.isChecked ||
            binding.checkBoxAnswer7.isChecked ||
            binding.checkBoxAnswer8.isChecked ||
            binding.checkBoxAnswer9.isChecked ||
            binding.checkBoxAnswer10.isChecked
        ) {
            navigateToNextPage()
        } else {
            val snackBar = Snackbar
                .make(binding.root, getString(R.string.make_a_choice), Snackbar.LENGTH_SHORT)
            snackBar.view.setBackgroundColor(Color.RED)
            snackBar.show()
            return
        }
    }

    private fun navigateToNextPage() {

        var counter = 0
        binding.linearLayout.forEach {
            if (it is MaterialCheckBox) {
                if (it.isChecked) {
                    viewModel.quiz.page[actualPage].question[0].answers[counter].result =
                        it.text.toString()
                } else {
                    viewModel.quiz.page[actualPage].question[0].answers[counter].result = ""
                }
                counter++
            }
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