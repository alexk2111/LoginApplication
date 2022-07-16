package com.onix.internship.okucherenko.ui.home

import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.HomeFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeFragmentBinding>(R.layout.home_fragment) {
    override val viewModel: HomeViewModel by viewModel()

    override fun setObservers() {
        super.setObservers()
        binding.homeViewModel = viewModel
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textView.text = it

        }
    }

}