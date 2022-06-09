package com.onix.okucherenko.loginapplication.ui.Page2

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
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

        binding.radioButtonAnswer1.text =
            viewModel.quiz.page[actualPage].question[0].answers[0].content
        binding.radioButtonAnswer2.text =
            viewModel.quiz.page[actualPage].question[0].answers[1].content
        binding.radioButtonAnswer3.text =
            viewModel.quiz.page[actualPage].question[0].answers[2].content
        binding.radioButtonAnswer4.text =
            viewModel.quiz.page[actualPage].question[0].answers[3].content
        binding.radioButtonAnswer5.text =
            viewModel.quiz.page[actualPage].question[0].answers[4].content

        binding.buttonPage2toPage3.setOnClickListener {
            onClickButtonPage2toPage3()
        }


    }

    private fun onClickButtonPage2toPage3() {

        if (!binding.radioButtonAnswer1.isChecked &&
            !binding.radioButtonAnswer2.isChecked &&
            !binding.radioButtonAnswer3.isChecked &&
            !binding.radioButtonAnswer4.isChecked &&
            !binding.radioButtonAnswer5.isChecked
        ) {
            val snackBar = Snackbar
                .make(binding.root, "Make a choice!!!", Snackbar.LENGTH_SHORT)
            snackBar.view.setBackgroundColor(Color.RED)
            snackBar.show()
            return
        }

        if (binding.radioButtonAnswer1.isChecked) {
            viewModel.quiz.page[actualPage].question[0].answers[0].result =
                binding.radioButtonAnswer1.text.toString()
        } else {
            viewModel.quiz.page[actualPage].question[0].answers[0].result = ""
        }
        if (binding.radioButtonAnswer2.isChecked) {
            viewModel.quiz.page[actualPage].question[0].answers[1].result =
                binding.radioButtonAnswer2.text.toString()
        } else {
            viewModel.quiz.page[actualPage].question[0].answers[1].result = ""
        }
        if (binding.radioButtonAnswer3.isChecked) {
            viewModel.quiz.page[actualPage].question[0].answers[2].result =
                binding.radioButtonAnswer3.text.toString()
        } else {
            viewModel.quiz.page[actualPage].question[0].answers[2].result = ""
        }
        if (binding.radioButtonAnswer4.isChecked) {
            viewModel.quiz.page[actualPage].question[0].answers[3].result =
                binding.radioButtonAnswer4.text.toString()
        } else {
            viewModel.quiz.page[actualPage].question[0].answers[3].result = ""
        }
        if (binding.radioButtonAnswer5.isChecked) {
            viewModel.quiz.page[actualPage].question[0].answers[4].result =
                binding.radioButtonAnswer5.text.toString()
        } else {
            viewModel.quiz.page[actualPage].question[0].answers[4].result = ""
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