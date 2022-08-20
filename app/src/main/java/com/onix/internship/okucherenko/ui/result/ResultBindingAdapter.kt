package com.onix.internship.okucherenko.ui.result

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

@BindingAdapter("userProgressBarVisible")
fun ProgressBar.setUserProgressBarVisible(progressVisible: Boolean) {
    visibility = if (progressVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

