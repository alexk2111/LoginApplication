package com.onix.okucherenko.loginapplication.model.quiz

data class Question(
    val answers: List<Answer>,
    val content: String,
    val type: Int
)