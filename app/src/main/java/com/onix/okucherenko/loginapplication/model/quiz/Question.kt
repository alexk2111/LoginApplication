package com.onix.okucherenko.loginapplication.model.quiz

data class Question(
    var answers: MutableList<Answer>,
    val content: String,
    val type: Int
)