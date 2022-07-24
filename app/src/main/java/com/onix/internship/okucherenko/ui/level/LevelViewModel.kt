package com.onix.internship.okucherenko.ui.level

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.data.repository.entity.Settings

class LevelViewModel: BaseViewModel() {

    var sliderValue: Int = Settings.currentSetting.level

//    private val _sliderLabel = MutableLiveData<String>()
//    val sliderLabel: LiveData<String> = _sliderLabel

   private val _prev = MutableLiveData<Boolean>()
    val prev: LiveData<Boolean> = _prev

    private val _next = MutableLiveData<Boolean>()
    val next: LiveData<Boolean> = _next

    init {
        _next.value = false
        _prev.value = false
    }

    fun onPrev(){
        _prev.value = true
    }
    fun onNext(){
        _next.value = true
    }

//    fun progressChanged(){
//        _sliderLabel.value = "${sliderValue.toString()} of 5"
//    }
}