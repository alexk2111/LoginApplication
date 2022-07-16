package com.onix.internship.okucherenko.ui.graphic

import androidx.fragment.app.viewModels
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.GraphicFragmentBinding

class GraphicFragment : BaseFragment<GraphicFragmentBinding>(R.layout.graphic_fragment) {
    override val viewModel: GraphicViewModel by viewModels()

    override fun setObservers() {
        super.setObservers()
        binding.graphicViewModel = viewModel
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textView.text = it
        }
    }
}
