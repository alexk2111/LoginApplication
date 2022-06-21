package com.onix.internship.okucherenko.arch.provider

import android.content.Context
import com.onix.internship.okucherenko.arch.provider.model.TextProvider

fun TextProvider.getString(context: Context) = when (this) {
    is TextProvider.Text -> text
    is TextProvider.ResText -> if (text == 0) "" else context.getString(text)
    is TextProvider.FormatResText -> if (res == 0) "" else context.getString(res, text)
}

fun String.toProvider(): TextProvider {
    return TextProvider.Text(text = this)
}