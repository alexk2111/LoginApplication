package com.onix.okucherenko.loginapplication.ui.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.google.gson.Gson
import com.onix.okucherenko.loginapplication.R
import com.onix.okucherenko.loginapplication.databinding.FragmentTitleBinding
import com.onix.okucherenko.loginapplication.model.quiz.Quiz

class TitleFragment : Fragment(R.layout.fragment_title) {

    private lateinit var viewModel: TitleViewModel
    private var fragmentTitleBinding: FragmentTitleBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentTitleBinding.bind(view)
        fragmentTitleBinding = binding

        viewModel = ViewModelProvider(this)[TitleViewModel::class.java]

        val jsonAssets = activity?.assets!!
        val inputStream = jsonAssets.open("quiz.json")
        viewModel.quiz = Gson().fromJson(inputStream.reader(), Quiz::class.java)

        binding.nameTest.text = viewModel.quiz.nameTest
        binding.buttonStart.setOnClickListener {
            onClickButtonStart()
        }
    }

    private fun onClickButtonStart() {
        viewModel.onClickStartButton()
        val amount = viewModel.modelQuiz
        val action = TitleFragmentDirections.actionTitleFragmentToPage1Fragment(amount)
        NavHostFragment.findNavController(this).navigate(action)
    }

    override fun onDestroy() {
        fragmentTitleBinding = null
        super.onDestroy()
    }
}