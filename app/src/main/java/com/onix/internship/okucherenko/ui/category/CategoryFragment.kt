package com.onix.internship.okucherenko.ui.category

import android.os.Bundle
import android.view.View
import androidx.core.view.forEachIndexed
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.radiobutton.MaterialRadioButton
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.data.repository.PreferenceStorage
import com.onix.internship.okucherenko.data.repository.entity.Settings
import com.onix.internship.okucherenko.databinding.CategoryFragmentBinding
import com.google.gson.Gson
import com.onix.internship.okucherenko.ui.main.MainScreen

class CategoryFragment : BaseFragment<CategoryFragmentBinding>(R.layout.category_fragment) {
    override val viewModel: CategoryViewModel by viewModels()

    override fun setObservers() {
        super.setObservers()

        binding.categoryViewModel = viewModel

        viewModel.prev.observe(viewLifecycleOwner) {
            if (it)
                findNavController().navigate(CategoryFragmentDirections.actionCategoryFragmentToLevelFragment())
        }

        viewModel.next.observe(viewLifecycleOwner) {
            if (it) {
                setItem()
                val storage = PreferenceStorage(requireContext())
                val valueToSave: String = Gson().toJson(Settings.currentSetting)
                storage.save(getString(R.string.saved_high_score_key), valueToSave)

                (activity as MainScreen).navView.visibility = View.VISIBLE

                findNavController().navigate(CategoryFragmentDirections.actionCategoryFragmentToMapFragment())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.radioGroup.forEachIndexed { index, view ->
            if (view is MaterialRadioButton && index == Settings.currentSetting.category)
                view.isChecked = true
        }

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->  
            setItem()
        }
    }

    private fun setItem() {
        binding.radioGroup.forEachIndexed { index, view ->
            if (view is MaterialRadioButton && view.isChecked)
                Settings.currentSetting.category = index
        }
    }
}