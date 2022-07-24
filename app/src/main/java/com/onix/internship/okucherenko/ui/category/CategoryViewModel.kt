package com.onix.internship.okucherenko.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.okucherenko.arch.BaseViewModel

class CategoryViewModel: BaseViewModel() {
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

}