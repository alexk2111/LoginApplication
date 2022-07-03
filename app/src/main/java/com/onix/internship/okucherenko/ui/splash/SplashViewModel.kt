package com.onix.internship.okucherenko.ui.splash

import android.content.Context
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.arch.lifecycle.SingleLiveEvent
import com.onix.internship.okucherenko.domain.usecase.XmlDictionaryParser
import kotlinx.coroutines.delay

class SplashViewModel : BaseViewModel() {

    val initEvent = SingleLiveEvent<Boolean>()
    lateinit var context: Context

    init {
        onLoading(true)
        launch {
            delay(1000)
            val parser = XmlDictionaryParser(context)
            parser.parse()
            initEvent.postValue(true)
        }
    }

}