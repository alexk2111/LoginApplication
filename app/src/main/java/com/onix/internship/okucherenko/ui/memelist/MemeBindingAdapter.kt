package com.onix.internship.okucherenko.ui.memelist

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.onix.internship.okucherenko.data.model.Data

@BindingAdapter("userProgressBarVisible")
fun ProgressBar.setUserProgressBarVisible(progressVisible: Boolean) {
    visibility = if (progressVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("memeImageSrc")
fun ImageView.setMemeImageSrc(item: Data) {
    Glide.with(this).load(item.image).into(this)
}