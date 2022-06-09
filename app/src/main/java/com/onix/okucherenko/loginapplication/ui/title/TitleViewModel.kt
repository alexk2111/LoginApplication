package com.onix.okucherenko.loginapplication.ui.title

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.onix.okucherenko.loginapplication.model.quiz.Quiz

class TitleViewModel: ViewModel() {

    lateinit var quiz: Quiz
    lateinit var modelQuiz: String

    fun onClickStartButton() {
        modelQuiz = Gson().toJson(quiz)
    }

}