package com.onix.internship.okucherenko.ui.result

import androidx.navigation.fragment.findNavController
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.ResultFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultFragment : BaseFragment<ResultFragmentBinding>(R.layout.result_fragment) {
    override val viewModel: ResultViewModel by viewModel()

    private val adapter = SongItemAdapter(RecordingListener { item ->
        viewModel.onDetails(item)
    })


    override fun setObservers() {
        super.setObservers()

        binding.resultViewModel = viewModel
        binding.recyclerViewSongs.adapter = adapter

        viewModel.error.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                showToast(it)
            }
        }

        viewModel.errorFirstPage.observe(viewLifecycleOwner) {
            if (it) {
                showToast(getString(R.string.error_first_page))
            }
        }

        viewModel.errorLastPage.observe(viewLifecycleOwner) {
            if (it) {
                showToast(getString(R.string.error_last_page))
            }
        }

        viewModel.recordings.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter.submitList(it.toList())
            } else {
                showToast("Empty")
            }
        }

        viewModel.details.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(ResultFragmentDirections.actionResultFragmentToDetailsFragment())
            }
        }
    }
}