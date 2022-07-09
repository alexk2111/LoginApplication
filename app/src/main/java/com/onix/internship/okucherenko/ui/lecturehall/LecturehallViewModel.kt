package com.onix.internship.okucherenko.ui.lecturehall

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.okucherenko.arch.BaseViewModel

class LecturehallViewModel : BaseViewModel() {

    lateinit var lecturehallSceneArray: Array<String>
    private var index = 0

    private var _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate

    private var _textMessage = MutableLiveData<String>()
    val textMessage: LiveData<String> = _textMessage

    init {
        _navigate.value = false
    }

    fun nextMessage(){
        if (index < lecturehallSceneArray.size) {
            _textMessage.value = lecturehallSceneArray[index]
            index++
        } else {
            _navigate.value = true
        }
    }
}