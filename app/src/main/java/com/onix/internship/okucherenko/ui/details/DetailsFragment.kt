package com.onix.internship.okucherenko.ui.details

import android.os.Bundle
import android.view.View
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.DetailsFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment<DetailsFragmentBinding>(R.layout.details_fragment) {
    override val viewModel: DetailsViewModel by viewModel()

    override fun setObservers() {
        super.setObservers()

        binding.detailsViewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageButtonDownload.setOnClickListener{

        }
    }
}