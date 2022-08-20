package com.onix.internship.okucherenko.ui.search.simple

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.ui.result.ResultViewModel

class SimpleViewModel : BaseViewModel() {

    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean> = _navigate

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    var textForSearch: String = ""

    fun onStart() {
        if (textForSearch.trim().isEmpty() || textForSearch == "") {
            _error.value = true
            return
        }
        ResultViewModel.querySimple = textForSearch
        _navigate.value = true
    }
}