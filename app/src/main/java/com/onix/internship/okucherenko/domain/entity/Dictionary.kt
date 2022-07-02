package com.onix.internship.okucherenko.domain.entity

object Dictionary {
    var langFrom: String = ""
    var langTo: String = ""
    var format: String = ""
    var fullName: String = ""
    var description: String = ""
    val dictionary = mutableListOf<DictionaryItem>()
}