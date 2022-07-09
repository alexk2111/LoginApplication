package com.onix.internship.okucherenko.ui.uni

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.domain.entity.SettingsGame

class UniViewModel: BaseViewModel() {

    lateinit var uniMenuArray: Array<String>
    lateinit var uniSceneArray: Array<String>
    private var index = 0

    private var _menuLayoutVisibility = MutableLiveData<Boolean>()
    val menuLayoutVisibility: LiveData<Boolean> = _menuLayoutVisibility

    private var _animationSilvie = MutableLiveData<Boolean>()
    val animationSilvie: LiveData<Boolean> = _animationSilvie

    private var _navigate = MutableLiveData<String>()
    val navigate: LiveData<String> = _navigate

    private var _textMessage = MutableLiveData<String>()
    val texMessage: LiveData<String> = _textMessage

    private val _rightaway = MutableLiveData<Boolean>()
    val rightaway: LiveData<Boolean> = _rightaway

    private val _later = MutableLiveData<Boolean>()
    val later: LiveData<Boolean> = _later
    init {
        _navigate.value = ""
        _animationSilvie.value = false
        _rightaway.value = false
        _later.value = false
        SettingsGame.blackScene = ""
    }

    fun nextMessage(){
        if (_menuLayoutVisibility.value == true){
            return
        }
        if (index < uniSceneArray.size) {
            if ((index == 1) && (_rightaway.value == false)){
                _animationSilvie.value = true
            }
            _textMessage.value = uniSceneArray[index]
        }
        index++

        if ((index == uniSceneArray.size) && (_rightaway.value == false) && (_later.value == false) ) {
            showMenu()
        }
        if ((index > uniSceneArray.size) && (_rightaway.value == true)) {
            _navigate.value = "meadow"
        }

        if ((index > uniSceneArray.size) && (_later.value == true)) {
            _navigate.value = "black"
        }
    }

    private fun showMenu() {
        index = 0
        _menuLayoutVisibility.value = true
    }

    fun onClickContinue(){
        _menuLayoutVisibility.value = false
        _rightaway.value = true
    }

    fun onClickExit(){
        _menuLayoutVisibility.value = false
        SettingsGame.blackScene = "later"
        _later.value = true
    }

}