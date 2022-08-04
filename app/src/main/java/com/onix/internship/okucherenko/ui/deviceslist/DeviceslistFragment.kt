package com.onix.internship.okucherenko.ui.deviceslist

import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.DeviceslistFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeviceslistFragment : BaseFragment<DeviceslistFragmentBinding>(R.layout.deviceslist_fragment),
    SwipeRefreshLayout.OnRefreshListener {
    override val viewModel: DeviceslistViewModel by viewModel()
    private val adapter = DeviceListAdapter(DeviceItemListener { id ->
        viewModel.onDelete(id ) })

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun setObservers() {
        super.setObservers()
        binding.divicelistViewModel = viewModel
        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(this)
        binding.recyclerList.adapter = adapter

        viewModel.progressVisible.observe(viewLifecycleOwner, Observer {
            if (!it) {
                swipeRefreshLayout.isRefreshing = it
            }
        })

        viewModel.devices.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it.toList())
            }
        })
    }

    override fun onRefresh() {
        viewModel.devicesFromSite(true)
    }


}