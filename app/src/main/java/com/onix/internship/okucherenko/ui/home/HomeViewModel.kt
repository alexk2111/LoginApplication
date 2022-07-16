package com.onix.internship.okucherenko.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.okucherenko.arch.BaseViewModel

class HomeViewModel: BaseViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Home Fragment in developing"
    }
    val text: LiveData<String> = _text
}