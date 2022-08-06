package com.onix.internship.okucherenko.ui.adddevice

import android.content.Context
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.data.repository.entity.DeviceItem
import com.onix.internship.okucherenko.ui.deviceslist.DeviceslistViewModel
import kotlin.random.Random

class AdddeviceViewModel(private val currentContext: Context) : BaseViewModel() {
//    val currentContext = context
private val pictureList = listOf(
        "https://mobimg.b-cdn.net/v3/fetch/9d/9db2d4683d92f5f2045e9142fbd82633.jpeg",
        "https://img2.akspic.ru/previews/2/9/0/9/6/169092/169092-sipuha-neyasyt-ptica-klyuv-naturalnyj_material-500x.jpg",
        "https://planbphoto.com/wp-content/uploads/Serze.jpg",
        "https://www.imgonline.com.ua/examples/bee-on-daisy.jpg",
        "https://klike.net/uploads/posts/2019-05/1556945364_1.jpg"
    )
    private val sensorList = listOf("on", "off")

    var newDevice = DeviceItem()
    var _typePosition = MutableLiveData<Int>()
    val typePosition: LiveData<Int> = _typePosition
    var _subtypePosition = MutableLiveData<Int>()
    val subtypePosition: LiveData<Int> = _subtypePosition

    private val _cancel = MutableLiveData<Boolean>()
    val cancel: LiveData<Boolean> = _cancel

    private val _add = MutableLiveData<Boolean>()
    val add: LiveData<Boolean> = _add

    fun onAdd() {
        when (newDevice.type) {
            currentContext.resources.getStringArray(R.array.type_devices)[0] -> {
                newDevice.value = sensorList[Random.nextInt(0, sensorList.size)]
            }
            currentContext.resources.getStringArray(R.array.type_devices)[1] -> {
                newDevice.value = pictureList[Random.nextInt(0, pictureList.size)]
            }
            currentContext.resources.getStringArray(R.array.type_devices)[2] -> {
                newDevice.value = Random.nextInt(0, 100).toString()
            }
            currentContext.resources.getStringArray(R.array.type_devices)[3] -> {
                newDevice.value = (Random.nextFloat()*100).toString().substring(0,5 )
            }
        }
        if (TextUtils.isEmpty(newDevice.room)){
            _add.value = false
        } else {
            DeviceslistViewModel.addNewDevice(newDevice)
            _add.value = true
        }
    }

    fun onCancel() {
        _cancel.value = true
    }
}