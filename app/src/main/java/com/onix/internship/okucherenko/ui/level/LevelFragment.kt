package com.onix.internship.okucherenko.ui.level

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.data.repository.entity.Settings
import com.onix.internship.okucherenko.databinding.LevelFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LevelFragment : BaseFragment<LevelFragmentBinding>(R.layout.level_fragment) {
    override val viewModel: LevelViewModel by viewModel()

    override fun setObservers() {
        super.setObservers()

        viewModel.next.observe(viewLifecycleOwner) {
            if (it)
                findNavController().navigate(LevelFragmentDirections.actionLevelFragmentToCategoryFragment())
        }
        viewModel.prev.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(LevelFragmentDirections.actionLevelFragmentToAgeFragment())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.levelViewModel = viewModel
        binding.slider.value = viewModel.sliderValue.toFloat()
        binding.textViewSliderLabel.text = "${viewModel.sliderValue} from ${binding.slider.valueTo}"

        binding.slider.addOnChangeListener { slider, value, fromUser ->
            Settings.currentSetting.level = binding.slider.value.toInt()
            binding.textViewSliderLabel.text = "$value from ${binding.slider.valueTo}"
        }
    }
}