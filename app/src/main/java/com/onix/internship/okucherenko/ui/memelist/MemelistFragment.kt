package com.onix.internship.okucherenko.ui.memelist

import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.MemelistFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MemelistFragment : BaseFragment<MemelistFragmentBinding>(R.layout.memelist_fragment) {
    override val viewModel: MemelistViewModel by viewModel()

    override fun setObservers() {
        super.setObservers()

        val adapter = MemeItemAdapter()
        binding.recyclerViewMemes.adapter = adapter

        binding.memeListViewModel = viewModel

        viewModel.error.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                showToast(it)
            }
        }

        viewModel.memesList.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }
    }
}