package com.onix.internship.okucherenko.data

import com.squareup.moshi.Json

data class Recording(
    val also: List<String>?,
    val alt: String?,
    @Json(name = "bird-seen") val birdSeen: String?,
    val cnt: String?,
    val date: String?,
    val en: String?,
    @Json(name = "file") val fileDown: String?,
    @Json(name = "file-name") val fileName: String?,
    val gen: String?,
    val id: String?,
    val lat: String?,
    val length: String?,
    val lic: String?,
    val lng: String?,
    val loc: String?,
    @Json(name = "playback-used") val playbackUsed: String?,
    val q: String?,
    val rec: String?,
    val rmk: String?,
    val sono: Sono,
    val sp: String?,
    val ssp: String?,
    val time: String?,
    val type: String?,
    val uploaded: String?,
    val url: String?
)