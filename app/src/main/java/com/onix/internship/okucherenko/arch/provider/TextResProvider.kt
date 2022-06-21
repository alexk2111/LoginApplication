package com.onix.internship.okucherenko.arch.provider

import android.content.Context
import com.onix.internship.okucherenko.arch.provider.model.TextProvider

class TextResProvider(private val context: Context) {

    fun getString(txtProvider: TextProvider): String {
        return txtProvider.getString(context)
    }
}