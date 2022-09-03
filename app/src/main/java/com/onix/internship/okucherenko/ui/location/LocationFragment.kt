package com.onix.internship.okucherenko.ui.location

import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.LocationFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationFragment : BaseFragment<LocationFragmentBinding>(R.layout.location_fragment) {
    override val viewModel: LocationViewModel by viewModel()
}