package com.onix.internship.okucherenko.ui.deviceslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.data.repository.entity.DeviceItem
import com.onix.internship.okucherenko.data.repository.entity.Devices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class DeviceslistViewModel : BaseViewModel() {

    private lateinit var onLineDevices: Devices

    private val _errorLoad = MutableLiveData<Boolean>()
    val errorLoad: LiveData<Boolean> = _errorLoad

    private val _addNavigate = MutableLiveData<Boolean>()
    val addNavigate: LiveData<Boolean> = _addNavigate

    private val _devices = MutableLiveData<MutableList<DeviceItem>>()
    val devices: LiveData<MutableList<DeviceItem>> = _devices

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> = _progressVisible


    init {
        _progressVisible.value = true
        devicesFromSite()
    }

    fun devicesFromSite() {
        launch {
            withContext(Dispatchers.IO) {
//                delay(3000)
                var getNext = false
                try {
                    val doc: Document =
                        Jsoup.connect("https://onix-systems.github.io/OnixAndroidInternship2022/")
                            .get()
                    val body = doc.body()
                    body.forEach {
                        if (getNext) {
                            onLineDevices = Json.decodeFromString(
                                it.text().replace('“', '"').replace('”', '"')
                            )
                            getNext = false
                        }
                        if (it.id() == "data-started") {
                            getNext = true
                        }
                    }
                } catch (e: IOException) {
                    _errorLoad.value = true
                }
            }
            onLineDevices.house.forEachIndexed { index, element ->
                currentDevices.forEach {
                    if (it.id == index) {
                        it.room = element.room
                        it.type = element.type
                        it.subtype = element.subtype
                        it.value = element.value
                    }
                }
                if (currentDevices.firstOrNull { it.id == index } == null) {
                    currentDevices.add(
                        index,
                        DeviceItem(
                            element.room,
                            element.type,
                            element.subtype,
                            element.value,
                            index
                        )
                    )

                }
            }
            _devices.value = currentDevices
            _progressVisible.value = false
        }
    }

    fun onDelete(id: Int) {
        var device: DeviceItem? = null
        currentDevices.forEach {
            if (it.id == id) {
                device = it
            }
        }
        if (device != null) {
            currentDevices.remove(device)
        }

        _devices.value = currentDevices
    }

    fun deviceAdded() {
        _devices.value = currentDevices
    }

    fun addDevice() {
        _addNavigate.value = true
    }

    companion object {
        val currentDevices = mutableListOf<DeviceItem>()
        fun addNewDevice(device: DeviceItem) {
            var id = currentDevices.maxOfOrNull { it.id }
            if (id == null || id < 100) {
                id = 100
            } else {
                id++
            }
            device.id = id
            currentDevices.add(device)
            DeviceslistViewModel().deviceAdded()
        }
    }
}