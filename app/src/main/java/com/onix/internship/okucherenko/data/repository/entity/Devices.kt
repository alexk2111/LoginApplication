package com.onix.internship.okucherenko.data.repository.entity

import kotlinx.serialization.*

@Serializable
data class Devices(
    val house: List<House>,
    val name: String,
    val version: String
)