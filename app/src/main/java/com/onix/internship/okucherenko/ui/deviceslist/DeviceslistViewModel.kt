package com.onix.internship.okucherenko.ui.deviceslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.data.repository.entity.DeviceItem
import com.onix.internship.okucherenko.data.repository.entity.Devices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import java.nio.file.Files.find

class DeviceslistViewModel : BaseViewModel() {

    private val _devices = MutableLiveData<MutableList<DeviceItem>>()
    val devices: LiveData<MutableList<DeviceItem>> = _devices

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> = _progressVisible

    val currentDevices = mutableListOf<DeviceItem>()
    val forDelete = mutableListOf<Int>()

    init {
        _progressVisible.value = true
        devicesFromSite(false)
    }

    fun devicesFromSite(refresh: Boolean) {
        launch {
            withContext(Dispatchers.IO) {
                delay(3000)
                var getNext = false
                try {
                    val doc: Document =
                        Jsoup.connect("https://onix-systems.github.io/OnixAndroidInternship2022/")
                            .get()
                    val body = doc.body()
                    body.forEach() {
                        if (getNext) {
                            onLineDevices = Json.decodeFromString<Devices>(
                                it.text().replace('“', '"').replace('”', '"')
                            )
                            getNext = false
                        }
                        if (it.id() == "data-started") {
                            getNext = true
                        }
                    }
                } catch (e: IOException) {
//                stringBuilder.append("Error : ").append(e.message).append("\n")
                }
            }
            onLineDevices.house.forEachIndexed { index, element ->
                if (refresh) {
                    currentDevices.forEach() {
                        if (it.id == index) {
                            it.room = element.room
                            it.type = element.type
                            it.subtype = element.subtype
                            it.value = element.value
                        }
                    }

                } else {
                    currentDevices.add(
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
//        forDelete.add(id)
        var device: DeviceItem? = null
        currentDevices.forEach() {
            if (it.id == id) {
                device = it
            }
        }
        if (device != null) {
            currentDevices.remove(device)
        }

        _devices.value = currentDevices
    }

    companion object {
        lateinit var onLineDevices: Devices
    }
}