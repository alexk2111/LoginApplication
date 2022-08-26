package com.onix.internship.okucherenko.ui.memelist

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.MemelistFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MemelistFragment : BaseFragment<MemelistFragmentBinding>(R.layout.memelist_fragment), SwipeRefreshLayout.OnRefreshListener {
    override val viewModel: MemelistViewModel by viewModel()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun setObservers() {
        super.setObservers()

        val adapter = MemeItemAdapter()

        binding.recyclerViewMemes.adapter = adapter
        binding.memeListViewModel = viewModel
        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(this)

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

        viewModel.progressVisible.observe(viewLifecycleOwner) {
            if (!it) {
                swipeRefreshLayout.isRefreshing = it
            }
        }

    }

    override fun onRefresh() {
        viewModel.getMemes()
    }
}