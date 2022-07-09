package com.onix.internship.okucherenko.ui.meadow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.okucherenko.arch.BaseViewModel

class MeadowViewModel: BaseViewModel() {

    lateinit var meadowMenuArray: Array<String>
    lateinit var meadowSceneArray: Array<String>
    private var index = 0

    private var _textMessage = MutableLiveData<String>()
    val texMessage: LiveData<String> = _textMessage

    private var _menuLayoutVisibility = MutableLiveData<Boolean>()
    val menuLayoutVisibility: LiveData<Boolean> = _menuLayoutVisibility

    private var _animationSilvie = MutableLiveData<Boolean>()
    val animationSilvie: LiveData<Boolean> = _animationSilvie

    private var _silvieSurprised = MutableLiveData<Boolean>()
    val silvieSurprised: LiveData<Boolean> = _silvieSurprised

    private var _silvieNormal = MutableLiveData<Boolean>()
    val silvieNormal: LiveData<Boolean> = _silvieNormal

    private var _silvieSmile = MutableLiveData<Boolean>()
    val silvieSmile: LiveData<Boolean> = _silvieSmile

    private var _answerMenu = MutableLiveData<String>()
    val answerMenu: LiveData<String> = _answerMenu

    private var _navigate = MutableLiveData<String>()
    val navigate: LiveData<String> = _navigate

    init {
        _navigate.value = ""
        _animationSilvie.value = false
        _silvieSurprised.value = false
        _silvieSmile.value = false
        _answerMenu.value = ""
    }

    fun nextMessage() {
        if (_menuLayoutVisibility.value == true){
            return
        }

        if (index < meadowSceneArray.size) {
            if ((index == 4)  && (_answerMenu.value == "")){
                _animationSilvie.value = true
            }
            if ((index == 8)  && (_answerMenu.value == "")){
                _silvieSurprised.value = true
            }
            if ((index == 7)  && (_answerMenu.value == "game")){
                _silvieNormal.value = true
            }
            if ((index == 1)  && (_answerMenu.value == "book")){
                _silvieSurprised.value = true
            }
            if ((index == 5)  && (_answerMenu.value == "book")){
                _silvieSmile.value = true
            }

            _textMessage.value = meadowSceneArray[index]
        }
        index++
        if ((index > meadowSceneArray.size) && (_answerMenu.value == "")){
            _silvieSmile.value = true
            index = 0
            showMenu()
        }
        if ((index > meadowSceneArray.size) && (_answerMenu.value !== "")){
            _navigate.value = _answerMenu.value
        }
    }

    private fun showMenu() {
        _textMessage.value = meadowMenuArray[0]
        _menuLayoutVisibility.value = true
    }

    fun onClickGame(){
        _answerMenu.value = "game"
        _menuLayoutVisibility.value = false
        nextMessage()
    }

    fun onClickBook(){
        _answerMenu.value = "book"
        _menuLayoutVisibility.value = false
        nextMessage()
    }
}