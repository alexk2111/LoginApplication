package com.onix.internship.ui.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.arch.BaseViewModel

class StartViewModel : BaseViewModel() {

    private val _firstStep = MutableLiveData<String>()
    val firstStep: LiveData<String>
        get() = _firstStep

    private val _userChip = MutableLiveData<String>()
    val userChip: LiveData<String>
        get() = _userChip

    private val _startGame = MutableLiveData<Boolean>()
    val startGame: LiveData<Boolean>
        get() = _startGame

    init {

        _firstStep.value = ""
        _userChip.value = ""
        _startGame.value = false

    }

    fun onClickFirstStepUser() {
        _firstStep.value = "User"
    }

    fun onClickFirstStepDevice() {
        _firstStep.value = "Device"
    }

    fun onClickUserChipX() {
        _userChip.value = "X"
    }

    fun onClickUserChipO() {
        _userChip.value = "O"
    }

    fun onStartGame() {
        _startGame.value = _firstStep.value != "" && _userChip.value != ""
    }
}