package com.onix.internship.okucherenko.data.model

data class MemePage(
    val code: Int,
    val data: List<Data>,
    val message: String,
    val next: String
)