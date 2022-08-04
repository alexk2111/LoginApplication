package com.onix.internship.okucherenko.ui.deviceslist

import android.opengl.Visibility
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.onix.internship.okucherenko.data.repository.entity.DeviceItem

@BindingAdapter("userProgressBarVisible")
fun ProgressBar.setUserProgressBarVisible(progressVisible: Boolean) {
    if (progressVisible) {
        visibility = View.VISIBLE
    } else {
        visibility = View.GONE
    }
}

@BindingAdapter("userTextViewVisible")
fun TextView.setUserTextViewVisible(item: DeviceItem) {
    if (item.value.contains("http")){
        visibility = View.GONE
    } else {
        visibility = View.VISIBLE
    }
}

@BindingAdapter("userImageVisible")
fun ImageView.setUserImageVisible(item: DeviceItem) {
    if (item.value.contains("http")){
        visibility = View.VISIBLE
        Glide.with(this).load(item.value).into(this)
    } else {
        visibility = View.GONE
    }
}