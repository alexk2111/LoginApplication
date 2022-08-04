package com.onix.internship.okucherenko.data.repository.entity

import kotlinx.serialization.*

@Serializable
data class House(
    val room: String,
    val type: String,
    val subtype: String,
    val value: String
)
