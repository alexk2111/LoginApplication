package com.onix.internship.okucherenko.ui.graphic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.okucherenko.arch.BaseViewModel

class GraphicViewModel: BaseViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Graphic Fragment in developing"
    }
    val text: LiveData<String> = _text

}