package com.onix.okucherenko.loginapplication.ui.Page2

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textview.MaterialTextView
import com.onix.okucherenko.loginapplication.R
import com.onix.okucherenko.loginapplication.databinding.FragmentPage2Binding

class Page2Fragment : Fragment(R.layout.fragment_page2) {

    private var fragmentPage2Binding: FragmentPage2Binding? = null

    private lateinit var viewModel: Page2ViewModel
    private lateinit var viewModelFactory: Page2ViewModelFactory
    private lateinit var binding: FragmentPage2Binding
    private var actualPage = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPage2Binding.bind(view)
        fragmentPage2Binding = binding

        viewModelFactory =
            Page2ViewModelFactory(Page2FragmentArgs.fromBundle(requireArguments()).modelQuiz)
        viewModel = ViewModelProvider(this, viewModelFactory)[Page2ViewModel::class.java]

        actualPage = viewModel.actualPage

        updateViewText(binding.page2ConstraintLayout, 0)

        binding.buttonPage2toPage3.setOnClickListener {
            onClickButtonPage2toPage3()
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
                is MaterialRadioButton -> {
                    view.text =
                        viewModel.quiz.page[actualPage].question[0].answers[numberAnswer].content
                    localNumberAnswer++
                }
            }
        }
        return localNumberAnswer
    }


    private fun onClickButtonPage2toPage3() {

        if (!binding.radioButtonAnswer1.isChecked &&
            !binding.radioButtonAnswer2.isChecked &&
            !binding.radioButtonAnswer3.isChecked &&
            !binding.radioButtonAnswer4.isChecked &&
            !binding.radioButtonAnswer5.isChecked
        ) {
            val snackBar = Snackbar
                .make(binding.root, getString(R.string.make_a_choice), Snackbar.LENGTH_SHORT)
            snackBar.view.setBackgroundColor(Color.RED)
            snackBar.show()
            return
        }

        var counter = 0
        binding.radioGroup.forEach {
            if (it is MaterialRadioButton) {
                if (it.isChecked) {
                    viewModel.quiz.page[actualPage].question[0].answers[counter].result =
                        it.text.toString()
                } else {
                    viewModel.quiz.page[actualPage].question[0].answers[counter].result = ""
                }
                counter++
            }
        }

        viewModel.onClickButtonPage2toPage3()
        val amount = viewModel.modelQuiz
        val action = Page2FragmentDirections.actionPage2FragmentToPage3Fragment(amount)
        NavHostFragment.findNavController(this).navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentPage2Binding = null
    }
}