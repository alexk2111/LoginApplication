package com.onix.internship.okucherenko.ui.search.extended

import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.ExtendedFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExtendedFragment : BaseFragment<ExtendedFragmentBinding>(R.layout.extended_fragment) {
    override val viewModel: ExtendedViewModel by viewModel()
}