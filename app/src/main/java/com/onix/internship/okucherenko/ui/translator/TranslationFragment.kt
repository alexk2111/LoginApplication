package com.onix.internship.okucherenko.ui.translator

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.TranslationFragmentBinding
import com.onix.internship.okucherenko.domain.entity.Dictionary
import com.onix.internship.okucherenko.ui.listoftranslations.ListoftranslationsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TranslationFragment :
    BaseFragment<TranslationFragmentBinding>(R.layout.translation_fragment) {
    override val viewModel: TranslationViewModel by viewModel()
    private val adapter = TranslateAdapter()

    override fun setObservers() {
        super.setObservers()

        viewModel.translationList.observe(viewLifecycleOwner, Observer {
            it?.let { it ->
                adapter.submitList(it.reversed())
                binding.editText.text.clear()
                ListoftranslationsFragment.resultCollection = viewModel.resultCollection
                ListoftranslationsFragment().show(childFragmentManager, ListoftranslationsFragment.TAG)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentContext = requireContext()
        binding.translateViewModel = viewModel
        binding.translateList.adapter = adapter
        binding.textViewFullName.text = Dictionary.fullName
        binding.radioButtonWord1.text = "${Dictionary.langFrom} -> ${Dictionary.langTo}"
        binding.radioButtonWord2.text = "${Dictionary.langTo} -> ${Dictionary.langFrom}"
    }
}