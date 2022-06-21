package com.onix.internship.okucherenko.ui.splash

import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.arch.lifecycle.SingleLiveEvent
import kotlinx.coroutines.delay

class SplashViewModel : BaseViewModel() {

    val initEvent = SingleLiveEvent<Boolean>()

    init {
        onLoading(true)
        launch {
            delay(1000)
            initEvent.postValue(true)
        }
    }

}