package com.onix.okucherenko.loginapplication.model.quiz

data class Quiz(
    val nameTest: String,
    val page: MutableList<Page>
)