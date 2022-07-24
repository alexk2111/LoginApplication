package com.onix.internship.okucherenko.ui.settings

import android.os.Bundle
import android.view.View
import androidx.core.view.forEachIndexed
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.gson.Gson
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.data.repository.PreferenceStorage
import com.onix.internship.okucherenko.data.repository.entity.Settings
import com.onix.internship.okucherenko.databinding.SettingsFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment<SettingsFragmentBinding>(R.layout.settings_fragment) {
    override val viewModel: SettingsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.settingsViewModel = viewModel
        binding.slider.value = viewModel.sliderValue.toFloat()
        binding.textViewSliderLabel.text = "${viewModel.sliderValue} from ${binding.slider.valueTo}"

        binding.slider.addOnChangeListener { slider, value, fromUser ->
            Settings.currentSetting.level = binding.slider.value.toInt()
            binding.textViewSliderLabel.text = "$value from ${binding.slider.valueTo}"
        }
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

    override fun onPause() {
        super.onPause()
        val storage = PreferenceStorage(requireContext())
        val valueToSave: String = Gson().toJson(Settings.currentSetting)
        storage.save(getString(R.string.saved_high_score_key), valueToSave)

    }
}