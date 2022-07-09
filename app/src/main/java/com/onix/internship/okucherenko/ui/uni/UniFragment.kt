package com.onix.internship.okucherenko.ui.uni

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.navigation.fragment.findNavController
import android.view.animation.AnimationUtils
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.UniFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UniFragment : BaseFragment<UniFragmentBinding>(R.layout.uni_fragment) {
    override val viewModel: UniViewModel by viewModel()

    override fun setObservers() {
        super.setObservers()
        viewModel.navigate.observe(viewLifecycleOwner) {
            when (it) {
                "meadow" -> findNavController().navigate(UniFragmentDirections.actionUniFragmentToMeadowFragment())
                "black" -> findNavController().navigate(UniFragmentDirections.actionUniFragmentToBlackFragment())
            }
        }
        viewModel.animationSilvie.observe(viewLifecycleOwner) {
            if (it) {
                val animation: Animation =
                    AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
                binding.imageView.startAnimation(animation)
            }
        }
        viewModel.menuLayoutVisibility.observe(viewLifecycleOwner) {
            if (it)
                binding.layoutMenu.visibility = View.VISIBLE
            else
                binding.layoutMenu.visibility = View.INVISIBLE
        }
        viewModel.rightaway.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.uniSceneArray = resources.getStringArray(R.array.uni_rightaway)
                binding.imageView.setImageResource(R.drawable.sylvie_green_smile)
                viewModel.nextMessage()
            }
        }

        viewModel.later.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.uniSceneArray = resources.getStringArray(R.array.uni_later)
                viewModel.nextMessage()
            }
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.uniSceneArray = resources.getStringArray(R.array.uni_scene)
        viewModel.uniMenuArray = resources.getStringArray(R.array.uni_menu)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.uniViewModel = viewModel
        viewModel.nextMessage()
    }
}