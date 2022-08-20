package com.onix.internship.okucherenko.data


data class Songs(
    val numPages: Int,
    val numRecordings: String?,
    val numSpecies: String?,
    val page: Int,
    val recordings: List<Recording>?
)