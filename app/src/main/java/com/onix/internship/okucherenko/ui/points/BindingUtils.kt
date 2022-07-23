package com.onix.internship.okucherenko.ui.points

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.data.repository.entity.Point

    @BindingAdapter("userSettingsFormatted")
    fun TextView.setUserSettingsFormatted(item: Point) {
        text = when (item.category) {
            0 -> "Hero Level ${item.level}"
            1 -> "Player Level ${item.level}"
            2 -> "Master Level ${item.level}"
            else -> "Unknown Level ${item.level}"
        }
    }

    @BindingAdapter("lastDate")
    fun TextView.setLastDate(item:Point){
        text = item.date.toString()
    }

    @BindingAdapter("categoryImage")
    fun ImageView.setCategoryImage(item: Point){
        setImageResource(when (item.category){
            0 -> R.drawable.ic_hero_24
            1 -> R.drawable.ic_player_24
            2 -> R.drawable.ic_master_24
            else -> R.drawable.ic_pin
        })
    }
