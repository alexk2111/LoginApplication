package com.onix.internship.okucherenko.ui.black

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.BlackFragmentBinding
import com.onix.internship.okucherenko.domain.entity.SettingsGame
import org.koin.androidx.viewmodel.ext.android.viewModel

class BlackFragment : BaseFragment<BlackFragmentBinding>(R.layout.black_fragment) {
    override val viewModel:BlackViewModel by viewModel()

    override fun setObservers() {
        super.setObservers()

        viewModel.exit.observe(viewLifecycleOwner) {
            if (it) activity?.finish()
        }
        viewModel.menuLayoutVisibility.observe(viewLifecycleOwner) {
            if (it)
                binding.layoutMenu.visibility = View.VISIBLE
            else
                binding.layoutMenu.visibility = View.INVISIBLE
        }
        viewModel.restart.observe(viewLifecycleOwner) {
            if (it) findNavController().navigate(BlackFragmentDirections.actionBlackFragmentToLecturehallFragment())
        }

        viewModel.navigate.observe(viewLifecycleOwner) {
            if (it) findNavController().navigate(BlackFragmentDirections.actionBlackFragmentToClubFragment())
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        when (SettingsGame.blackScene){
            "later" -> viewModel.blackSceneArray = resources.getStringArray(R.array.black_later)
            "marry" -> viewModel.blackSceneArray = resources.getStringArray(R.array.black_marry)
            "marry_end" -> viewModel.blackSceneArray = resources.getStringArray(R.array.black_marry_end)
        }

        viewModel.blackMenuArray = resources.getStringArray(R.array.black_menu)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.blackViewModel = viewModel
        viewModel.nextMessage()
    }

}