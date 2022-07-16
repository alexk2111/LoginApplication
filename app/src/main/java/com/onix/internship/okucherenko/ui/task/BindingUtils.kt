package com.onix.internship.okucherenko.ui.task

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.onix.internship.okucherenko.data.repository.entity.MyWeekDate

@BindingAdapter("itemDateTextViewDayValue")
fun TextView.setItemDateTextViewDayValue(item: MyWeekDate) {
    text = item.weekDay.day.toString()
}

@BindingAdapter("itemDateTextViewDayName")
fun TextView.setItemDateTextViewDayName(item: MyWeekDate) {
    text = item.weekDayName
}
