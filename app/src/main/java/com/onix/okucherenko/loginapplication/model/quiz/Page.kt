package com.onix.okucherenko.loginapplication.model.quiz

data class Page(
    val number: Int,
    val question: MutableList<Question>
)