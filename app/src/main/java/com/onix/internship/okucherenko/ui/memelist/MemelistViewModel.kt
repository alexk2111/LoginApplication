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
        getMemes()
    }

    private fun getMemes() {
        viewModelScope.launch {
            _progressVisible.value = true
            try {
                val result = MemeApi.retrofitMemeService.getMemes(nextPage)
                val resultList = mutableListOf<Data>()
                resultList.addAll(result.data)
                _memesList.value = resultList
            } catch (e: Exception) {
                _error.value = "Failure: ${e.message}"
            }
            _progressVisible.value = false

            val sss = "{\"code\": 200,\"data\":[{\"ID\": 1,\"bottomText\": \"Good!\",\"image\": \"http://imgflip.com/s/meme/Grumpy-Cat.jpg\",\"name\": \"Grumpy Cat\",\"tags\": \"Tardar Sauce, Tabatha Bundesen, Felis domesticus\",\"topText\": \"\"}],\"message\": \"GET successful\",\"next\": \"http://alpha-meme-maker.herokuapp.com/2\" }"

            val result: MemePage = Gson().fromJson(sss,MemePage::class.java)
            val resultList = mutableListOf<Data>()
            resultList.addAll(result.data)
            _memesList.value = resultList


        }

    }

}