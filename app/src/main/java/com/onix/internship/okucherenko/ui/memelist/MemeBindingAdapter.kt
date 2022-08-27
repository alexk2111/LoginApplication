package com.onix.internship.okucherenko.ui.memelist

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.onix.internship.okucherenko.data.model.Data

@BindingAdapter("memeImageSrc")
fun ImageView.setMemeImageSrc(item: Data) {
    Glide.with(this).load(item.image).into(this)
}