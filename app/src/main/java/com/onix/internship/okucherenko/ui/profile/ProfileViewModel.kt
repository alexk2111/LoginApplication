package com.onix.internship.okucherenko.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.okucherenko.arch.BaseViewModel

class ProfileViewModel: BaseViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Profile Fragment in developing"
    }
    val text: LiveData<String> = _text
}