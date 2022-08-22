package com.onix.internship.okucherenko.ui.search.simple

import androidx.navigation.fragment.findNavController
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.SimpleFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SimpleFragment : BaseFragment<SimpleFragmentBinding>(R.layout.simple_fragment) {
    override val viewModel: SimpleViewModel by viewModel()

    override fun setObservers() {
        super.setObservers()

        binding.simpleViewModel = viewModel
        viewModel.error.observe(viewLifecycleOwner) {
            if (it){
                binding.simpleSearchString.error = getString(R.string.not_empty)
            }
        }
        viewModel.navigate.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.resultFragment)
            }
        }
    }
}