package com.onix.internship.okucherenko.data.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("ID") val id : Int,
    val bottomText: String,
    val image: String,
    val name: String,
    val tags: String,
    val topText: String
)