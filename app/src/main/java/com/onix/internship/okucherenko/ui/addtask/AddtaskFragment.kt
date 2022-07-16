package com.onix.internship.okucherenko.ui.addtask

import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.AddtaskFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddtaskFragment : BaseFragment<AddtaskFragmentBinding>(R.layout.addtask_fragment) {
    override val viewModel: AddtaskViewModel by viewModel()


}