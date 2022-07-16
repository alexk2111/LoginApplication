package com.onix.internship.okucherenko.ui.profile

import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.ProfileFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<ProfileFragmentBinding>(R.layout.profile_fragment) {
    override val viewModel: ProfileViewModel by viewModel()

    override fun setObservers() {
        super.setObservers()
        binding.profileViewModel = viewModel
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textView.text = it

        }
    }
}