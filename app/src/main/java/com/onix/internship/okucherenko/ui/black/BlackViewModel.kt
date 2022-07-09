package com.onix.internship.okucherenko.ui.black

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.domain.entity.SettingsGame

class BlackViewModel: BaseViewModel() {

    lateinit var blackMenuArray: Array<String>
    lateinit var blackSceneArray: Array<String>
    private var index = 0

    private var _textMessage = MutableLiveData<String>()
    val texMessage: LiveData<String> = _textMessage

    private var _menuLayoutVisibility = MutableLiveData<Boolean>()
    val menuLayoutVisibility: LiveData<Boolean> = _menuLayoutVisibility

    private var _restart = MutableLiveData<Boolean>()
    val restart: LiveData<Boolean> = _restart

    private var _exit = MutableLiveData<Boolean>()
    val exit: LiveData<Boolean> = _exit

    private var _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean> = _navigate

    fun nextMessage(){
        if (_menuLayoutVisibility.value == true){
            return
        }

        if (index < blackSceneArray.size) {
            _textMessage.value = blackSceneArray[index]
        }
        index++

        if ((index > blackSceneArray.size) && (SettingsGame.blackScene !== "marry")) {
            index = 0
            showMenu()
        }

        if ((index > blackSceneArray.size) && (SettingsGame.blackScene == "marry")){
            _navigate.value = true
        }
    }

    private fun showMenu() {
        _textMessage.value = blackMenuArray[0]
        _menuLayoutVisibility.value = true
    }

    fun onClickContinue(){
        _menuLayoutVisibility.value = false
        _restart.value = true
    }

    fun onClickExit(){
        _menuLayoutVisibility.value = false
        _exit.value = true
    }
}