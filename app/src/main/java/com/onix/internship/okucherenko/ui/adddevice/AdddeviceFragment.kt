package com.onix.internship.okucherenko.ui.adddevice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.databinding.AdddeviceFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdddeviceFragment : DialogFragment() {

    private val viewModel: AdddeviceViewModel by viewModel()

    lateinit var binding: AdddeviceFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.adddevice_fragment, container, false)
        binding.adddeviceViewModel = viewModel
        viewModel.cancel.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
            }
        }

        viewModel.add.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.deviceslistFragment)
            } else {
                binding.editTextRoomName.error = getString(R.string.error_empty)
            }
        }

        viewModel.typePosition.observe(viewLifecycleOwner) {
            viewModel.newDevice.type = binding.spinnerType.getItemAtPosition(it).toString()
        }

        viewModel.subtypePosition.observe(viewLifecycleOwner) {
            viewModel.newDevice.subtype = binding.spinnerSubType.getItemAtPosition(it).toString()
        }

        return binding.root

    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }


}