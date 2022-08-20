package com.onix.internship.okucherenko.ui.result

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.data.Recording
import com.onix.internship.okucherenko.data.Songs
import com.onix.internship.okucherenko.network.SongsApi
import com.onix.internship.okucherenko.ui.details.DetailsViewModel
import kotlinx.coroutines.launch

class ResultViewModel : BaseViewModel() {

    private var recordingsList = mutableListOf<Recording>()
    private lateinit var listResult: Songs

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> = _progressVisible

    private val _recordings = MutableLiveData<MutableList<Recording>>()
    val recordings: LiveData<MutableList<Recording>> = _recordings

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _errorFirstPage = MutableLiveData<Boolean>()
    val errorFirstPage: LiveData<Boolean> = _errorFirstPage

    private val _errorLastPage = MutableLiveData<Boolean>()
    val errorLastPage: LiveData<Boolean> = _errorLastPage

    private val _details = MutableLiveData<Boolean>()
    val details: LiveData<Boolean> = _details

    var currentPage = ObservableField<String>()
    var numPages = ObservableField<String>()

    init {
        getSongs(1)
    }

    private fun getSongs(page: Int) {
        viewModelScope.launch {
            _progressVisible.value = true
            try {
                recordingsList = mutableListOf()
                listResult = SongsApi.retrofitService.getSongs(querySimple, page)
                listResult.recordings?.let { recordingsList.addAll(it) }
                currentPage.set(listResult.page.toString())
                numPages.set(listResult.numPages.toString())
                _recordings.value = recordingsList
            } catch (e: Exception) {
                _error.value = "Failure: ${e.message}"
            }
            _progressVisible.value = false
        }
    }

    fun onNextPage() {
        if (listResult.page < listResult.numPages) {
            getSongs(listResult.page + 1)
            return
        }
        _errorLastPage.value = true
    }

    fun onPrevPage(){
        if (listResult.page > 1) {
            getSongs(listResult.page - 1)
            return
        }
        _errorFirstPage.value = true
    }

    fun onDetails(item: Recording) {
        DetailsViewModel.item = item
        _details.value = true

    }

    companion object {
        var querySimple: String = ""
    }
}