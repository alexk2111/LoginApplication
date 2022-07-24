package com.onix.internship.okucherenko.ui.age

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.AgeFragmentBinding

class AgeFragment : BaseFragment<AgeFragmentBinding>(R.layout.age_fragment) {
    override val viewModel: AgeViewModel by viewModels()

    override fun setObservers() {
        super.setObservers()

        binding.ageViewModel = viewModel
        viewModel.next.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(AgeFragmentDirections.actionAgeFragmentToLevelFragment())
            }
        }
    }
}