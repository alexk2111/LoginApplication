package com.onix.internship.ui.game

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.onix.internship.R
import com.onix.internship.arch.BaseFragment
import com.onix.internship.databinding.GameFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GameFragment : BaseFragment<GameFragmentBinding>(R.layout.game_fragment) {

    private val args: GameFragmentArgs by navArgs()
    override val viewModel: GameViewModel by viewModel{ parametersOf(args.firstStep, args.userChip)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gameViewModel = viewModel
        binding.gameCanvas.canvasFirstStep = args.firstStep
        binding.gameCanvas.canvasUserChip = args.userChip
    }
}