package com.onix.internship.okucherenko.ui.emergency

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.databinding.EmergencyFragmentBinding

class EmergencyFragment : BaseFragment<EmergencyFragmentBinding>(R.layout.emergency_fragment) {
    override val viewModel: EmergencyViewModel by viewModels()
}