package com.onix.internship.okucherenko.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.data.repository.entity.ActualDate
import com.onix.internship.okucherenko.data.repository.entity.MyDate
import com.onix.internship.okucherenko.data.repository.entity.MyWeekDate
import com.onix.internship.okucherenko.ui.dialogs.DateDialog.Companion.actualDate
import java.util.*

class TaskViewModel : BaseViewModel() {

    lateinit var weekDaysName: Array<String>
    private lateinit var today: Calendar

    private val _weekArr = MutableLiveData<MutableList<MyWeekDate>>()
    val weekArr: LiveData<MutableList<MyWeekDate>> = _weekArr

    private val _month = MutableLiveData<String>()
    val month: LiveData<String> = _month

    private val _showDateDialog = MutableLiveData<Boolean>()
    val showDateDialog: LiveData<Boolean> = _showDateDialog

    private val _showToday = MutableLiveData<Boolean>()
    val showToday: LiveData<Boolean> = _showToday


    init {
        _weekArr.value = mutableListOf()
        _showDateDialog.value = false
    }

    fun getWeek(localDate: Calendar) {
        val firstWeekDay = 1
        val lastWeekDay = 7
        val mondayDayOfWeek = 1 // Monday
        ActualDate.actualDate = MyDate(localDate.get(Calendar.YEAR),
            localDate.get(Calendar.MONTH) + 1,
            localDate.get(Calendar.DAY_OF_MONTH))

        _month.value =
            localDate.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.getDefault())

        var dayOfWeek = localDate.get(Calendar.DAY_OF_WEEK)
        if (dayOfWeek == 1) dayOfWeek = 7
        else --dayOfWeek
        localDate.add(Calendar.DAY_OF_MONTH, (mondayDayOfWeek - dayOfWeek))
        val weekList = mutableListOf<MyWeekDate>()

        for (i in firstWeekDay..lastWeekDay) {
            weekList.add(
                MyWeekDate(
                    weekDaysName[i - 1],
                    MyDate(
                        localDate.get(Calendar.YEAR),
                        localDate.get(Calendar.MONTH) + 1,
                        localDate.get(Calendar.DAY_OF_MONTH)
                    )
                )
            )
            localDate.add(Calendar.DAY_OF_MONTH, 1)
        }

        _weekArr.value = weekList

        today = Calendar.getInstance()
        _showToday.value = ActualDate.actualDate == MyDate(today.get(Calendar.YEAR),today.get(Calendar.MONTH) + 1, today.get(Calendar.DAY_OF_MONTH))
    }

    fun getNewDate() {
        actualDate = ActualDate.actualDate
        _showDateDialog.value = true

    }
}