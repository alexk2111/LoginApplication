package com.onix.internship.okucherenko.ui.details

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.onix.internship.okucherenko.data.Recording

@BindingAdapter("userImageSrc")
fun ImageView.setUserImageSrc(item: Recording) {
        Glide.with(this).load("https:" + item.sono.med).into(this)
}