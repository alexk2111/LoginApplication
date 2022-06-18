package com.onix.internship.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GameViewModelFactory(private val finalFirstStep: String, private val finalUserChip: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(finalFirstStep, finalUserChip) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}