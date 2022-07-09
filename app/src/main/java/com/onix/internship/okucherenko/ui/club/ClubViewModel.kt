package com.onix.internship.okucherenko.ui.club

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.domain.entity.SettingsGame

class ClubViewModel : BaseViewModel() {

    lateinit var clubSceneArray: Array<String>
    private var index = 0
    var sylvieAnimation = true

    private var _textMessage = MutableLiveData<String>()
    val texMessage: LiveData<String> = _textMessage

    private var _sylvieBlueNormal = MutableLiveData<Boolean>()
    val sylvieBlueNormal: LiveData<Boolean> = _sylvieBlueNormal

    private var _sylvieBlueGiggle = MutableLiveData<Boolean>()
    val sylvieBlueGiggle: LiveData<Boolean> = _sylvieBlueGiggle

    private var _sylvieBlueSmile = MutableLiveData<Boolean>()
    val sylvieBlueSmile: LiveData<Boolean> = _sylvieBlueSmile

    private var _sylvieBlueSurprised = MutableLiveData<Boolean>()
    val sylvieBlueSurprised: LiveData<Boolean> = _sylvieBlueSurprised

    private var _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean> = _navigate

    fun nextMessage() {

        if (index < clubSceneArray.size) {

            if (index == 1 && SettingsGame.visualNovel != "book") {
                index++
            }

            when (index) {
                4 -> _sylvieBlueNormal.value = true
                6 -> _sylvieBlueGiggle.value = true
                8 -> _sylvieBlueSurprised.value = true
                10 -> _sylvieBlueSmile.value = true
                13 -> _sylvieBlueGiggle.value = true
                14 -> {
                    sylvieAnimation = false
                    _sylvieBlueNormal.value = true
                }
                18 -> _sylvieBlueGiggle.value = true
            }
            _textMessage.value = clubSceneArray[index]
        }
        index++

        if (index > clubSceneArray.size) {
            index = 0
            SettingsGame.blackScene = "marry_end"
            _navigate.value = true
        }
    }
}