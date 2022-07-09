package com.onix.internship.okucherenko.ui.meadow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.MeadowFragmentBinding
import com.onix.internship.okucherenko.domain.entity.SettingsGame
import org.koin.androidx.viewmodel.ext.android.viewModel

class MeadowFragment : BaseFragment<MeadowFragmentBinding>(R.layout.meadow_fragment) {
    override val viewModel: MeadowViewModel by viewModel()

    override fun setObservers() {
        super.setObservers()
        viewModel.menuLayoutVisibility.observe(viewLifecycleOwner) {
            if (it)
                binding.layoutMenu.visibility = View.VISIBLE
            else
                binding.layoutMenu.visibility = View.INVISIBLE
        }

        viewModel.animationSilvie.observe(viewLifecycleOwner) {
            if (it) {
                val animation: Animation =
                    AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
                binding.imageView.startAnimation(animation)
            }
        }

        viewModel.silvieSurprised.observe(viewLifecycleOwner) {
            if (it)
                binding.imageView.setImageResource(R.drawable.sylvie_green_surprised)
        }

        viewModel.silvieSmile.observe(viewLifecycleOwner) {
            if (it)
                binding.imageView.setImageResource(R.drawable.sylvie_green_smile)
        }

        viewModel.silvieNormal.observe(viewLifecycleOwner) {
            if (it)
                binding.imageView.setImageResource(R.drawable.sylvie_green_normal)
        }

        viewModel.answerMenu.observe(viewLifecycleOwner) { finalAnswer ->
            when (finalAnswer) {
                "game" -> viewModel.meadowSceneArray = resources.getStringArray(R.array.meadow_game)
                "book" -> viewModel.meadowSceneArray = resources.getStringArray(R.array.meadow_book)
            }
            SettingsGame.visualNovel = finalAnswer
        }

        viewModel.navigate.observe(viewLifecycleOwner) { finalNavigate ->
            navigateNext(finalNavigate)
        }
    }

    private fun navigateNext(finalNavigate: String) {
        if (finalNavigate != "") {
            SettingsGame.blackScene = "marry"
            findNavController().navigate(MeadowFragmentDirections.actionMeadowFragmentToBlackFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.meadowSceneArray = resources.getStringArray(R.array.meadow_scene)
        viewModel.meadowMenuArray = resources.getStringArray(R.array.meadow_menu)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.meadowViewModel = viewModel
        viewModel.nextMessage()
    }
}