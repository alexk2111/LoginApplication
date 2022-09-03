package com.onix.internship.okucherenko.ui.settings

import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.SettingsFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment<SettingsFragmentBinding>(R.layout.settings_fragment) {
    override val viewModel: SettingsViewModel by viewModel()
}