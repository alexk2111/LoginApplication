package com.onix.internship.okucherenko.ui.wifimanager

import android.net.wifi.ScanResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.domain.entity.WiFiDot

class ManagerViewModel : BaseViewModel() {

    private var wifiDotsMutableList: MutableList<WiFiDot> = mutableListOf()
    private var _wifiList = MutableLiveData<MutableList<WiFiDot>>()
    val wifiList: LiveData<MutableList<WiFiDot>>
        get() = _wifiList

    var accessFineLocation: Boolean = false
    var accessCoarseLocation: Boolean = false
    private var _accessLocation = MutableLiveData<Boolean>()
    val accessLocation: LiveData<Boolean>
    get() = _accessLocation

    private fun resetList() {
        wifiDotsMutableList = mutableListOf()
    }

    private fun addListRecord(wiFiDot: WiFiDot) {
        wifiDotsMutableList.add(wiFiDot)
    }

    fun updateWifiLiveData(results: MutableList<ScanResult>) {
        resetList()
        results.forEach { addListRecord(WiFiDot(it.BSSID, it.level + 100)) }
        wifiDotsMutableList.sortBy { it.level }
        _wifiList.value = wifiDotsMutableList.takeLast(8) as MutableList<WiFiDot>
    }

    fun checkAccessLocation() {
        if (accessFineLocation || accessCoarseLocation) {
            _accessLocation.value = true
        }
    }
}