package com.onix.okucherenko.loginapplication.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultViewModel(finalUserName: String) : ViewModel() {

    var userName = finalUserName

}

class ResultViewModelFactory(private val finalUserName: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(finalUserName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}