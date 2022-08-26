package com.onix.internship.okucherenko.ui.memelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.data.model.Data
import com.onix.internship.okucherenko.data.model.MemePage
import com.onix.internship.okucherenko.network.MemeApi
import kotlinx.coroutines.launch

class MemelistViewModel : BaseViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> = _progressVisible

    private val _memesList = MutableLiveData<MutableList<Data>>()
    val memesList: LiveData<MutableList<Data>> = _memesList

    var nextPage = "http://alpha-meme-maker.herokuapp.com/1"

    init {
        _progressVisible.value = true
        getMemes()
    }

    fun getMemes() {
        viewModelScope.launch {
            try {
                val result = MemeApi.retrofitMemeService.getMemes(1)
                val resultList = mutableListOf<Data>()
                resultList.addAll(result.data)
                _memesList.value = resultList
            } catch (e: Exception) {
                _error.value = "Failure: ${e.message}"
                val sss = "{\"code\": 200,\"data\":[{\"ID\": 1,\"bottomText\": \"Good!\",\"image\": \"http://imgflip.com/s/meme/Grumpy-Cat.jpg\",\"name\": \"Grumpy Cat\",\"tags\": \"Tardar Sauce, Tabatha Bundesen, Felis domesticus\",\"topText\": \"\"},{\"ID\": 1,\"bottomText\": \"Good!\",\"image\": \"http://imgflip.com/s/meme/Grumpy-Cat.jpg\",\"name\": \"Grumpy Cat\",\"tags\": \"Tardar Sauce, Tabatha Bundesen, Felis domesticus\",\"topText\": \"TopText\"}],\"message\": \"GET successful\",\"next\": \"http://alpha-meme-maker.herokuapp.com/2\" }"
                val result: MemePage = Gson().fromJson(sss,MemePage::class.java)
                val resultList = mutableListOf<Data>()
                resultList.addAll(result.data)
                _memesList.value = resultList
            }
            _progressVisible.value = false

        }
    }
}