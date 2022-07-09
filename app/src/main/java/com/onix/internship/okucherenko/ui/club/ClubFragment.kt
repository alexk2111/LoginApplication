package com.onix.internship.okucherenko.ui.club

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.ClubFragmentBinding

class ClubFragment : BaseFragment<ClubFragmentBinding>(R.layout.club_fragment) {
    override val viewModel: ClubViewModel by viewModels()

    override fun setObservers() {
        super.setObservers()
        viewModel.sylvieBlueNormal.observe(viewLifecycleOwner) {
            if (it) {
                if (viewModel.sylvieAnimation) {
                    val animation: Animation =
                        AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
                    binding.imageView.startAnimation(animation)
                } else {
                    binding.imageView.setImageResource(R.drawable.sylvie_blue_normal)
                }
            }
        }

        viewModel.sylvieBlueGiggle.observe(viewLifecycleOwner) {
            if (it)
                binding.imageView.setImageResource(R.drawable.sylvie_blue_giggle)
        }

        viewModel.sylvieBlueSmile.observe(viewLifecycleOwner) {
            if (it)
                binding.imageView.setImageResource(R.drawable.sylvie_blue_smile)
        }
        viewModel.sylvieBlueSurprised.observe(viewLifecycleOwner) {
            if (it)
                binding.imageView.setImageResource(R.drawable.sylvie_blue_surprised)
        }
        viewModel.navigate.observe(viewLifecycleOwner) {
            if (it) findNavController().navigate(ClubFragmentDirections.actionClubFragmentToBlackFragment())
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.clubSceneArray = resources.getStringArray(R.array.club_scene)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.clubViewModel = viewModel
        viewModel.nextMessage()
    }

}