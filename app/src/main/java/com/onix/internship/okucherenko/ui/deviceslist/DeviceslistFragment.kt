package com.onix.internship.okucherenko.ui.deviceslist

import androidx.navigation.fragment.findNavController
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
        binding.devicelistViewModel = viewModel
        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(this)
        binding.recyclerList.adapter = adapter

        viewModel.progressVisible.observe(viewLifecycleOwner) {
            if (!it) {
                swipeRefreshLayout.isRefreshing = it
            }
        }

        viewModel.devices.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it.toList())
            }
        }

        viewModel.addNavigate.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(DeviceslistFragmentDirections.actionDeviceslistFragmentToAdddeviceFragment())
            }
        }

        viewModel.errorLoad.observe(viewLifecycleOwner) {
            if (it) {
                showToast(getString(R.string.error_load))
            }
        }
    }

    override fun onRefresh() {
        viewModel.devicesFromSite()
    }


}