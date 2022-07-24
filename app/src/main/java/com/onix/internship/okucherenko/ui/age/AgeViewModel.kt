package com.onix.internship.okucherenko.ui.age

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.data.repository.entity.Settings

class AgeViewModel : BaseViewModel() {

    var statusSwitch = Settings.currentSetting.age

    private val _ageEnabledNext = MutableLiveData<Boolean>()
    val ageEnabledNext: LiveData<Boolean> = _ageEnabledNext

    private val _next = MutableLiveData<Boolean>()
    val next: LiveData<Boolean> = _next

    init {
        checkNext()
    }

    private fun checkNext() {
        _ageEnabledNext.value = statusSwitch
    }

    fun onSwitchClick() {
        Settings.currentSetting.age = statusSwitch
        checkNext()

    }

    fun onNext() {
        _next.value = true
    }

}