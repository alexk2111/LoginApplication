package com.onix.internship.okucherenko.ui.lecturehall

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.LecturehallFragmentBinding
import com.onix.internship.okucherenko.media.MediaService
import org.koin.androidx.viewmodel.ext.android.viewModel

class LecturehallFragment :
    BaseFragment<LecturehallFragmentBinding>(R.layout.lecturehall_fragment) {
    override val viewModel: LecturehallViewModel by viewModel()

    override fun setObservers() {
        super.setObservers()
        viewModel.navigate.observe(viewLifecycleOwner) {
            if (it)
                findNavController().navigate(LecturehallFragmentDirections.actionLecturehallFragmentToUniFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.lecturehallSceneArray = resources.getStringArray(R.array.lecturehall_scene)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lecturehallViewModel = viewModel
        viewModel.nextMessage()

        binding.buttonMenu.setOnClickListener {
            binding.navLecturehall.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.exit -> activity?.finish()
                    R.id.restart -> findNavController().navigate(R.id.lecturehallFragment)
                    R.id.switchMusic -> {
                        if(it.isChecked) {
                            activity?.startService(Intent(activity, MediaService::class.java))
                        } else {
                            activity?.stopService(Intent(activity, MediaService::class.java))
                        }
                    }
                }
                true
            }
            binding.drawerLecturehall.openDrawer(GravityCompat.START)
        }
    }

}