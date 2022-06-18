package com.onix.internship.ui.start

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.onix.internship.R
import com.onix.internship.arch.BaseFragment
import com.onix.internship.databinding.StartFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : BaseFragment<StartFragmentBinding>(R.layout.start_fragment) {

    override val viewModel: StartViewModel by viewModel()
    private var firstStep = ""
    private var userChip = ""

    override fun setObservers() {
        super.setObservers()
        binding.startViewModel = viewModel
        viewModel.startGame.observe(viewLifecycleOwner) { toGameScreen(it) }
        viewModel.firstStep.observe(viewLifecycleOwner, Observer { newFirstStep ->
            firstStep = newFirstStep
        })
        viewModel.userChip.observe(viewLifecycleOwner, Observer { newUserChip ->
            userChip = newUserChip
        })
    }

    private fun toGameScreen(startGame: Boolean) {
        if (startGame) {
            val action = StartFragmentDirections.actionStartFragmentToGameFragment(firstStep, userChip)
            findNavController().navigate(action)
        }
    }
}