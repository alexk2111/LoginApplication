package com.onix.okucherenko.loginapplication.ui.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.onix.okucherenko.loginapplication.model.quiz.Quiz

class ResultViewModel(finalQuiz: String) : ViewModel() {

    var resultTest = ""

    init {
        val quiz: Quiz = Gson().fromJson(finalQuiz, Quiz::class.java)
        val pages = quiz.page
        resultTest += quiz.nameTest + "\n"

        for (value in pages) {
            val page = value
            resultTest += "\n"
            val questions = page.question
            for (value in questions) {
                resultTest += value.content + "\n"
                val answers = value.answers
                for (value in answers) {
                    resultTest += value.content + "\n"
                }

            }
        }

    }
}

class ResultViewModelFactory(private val finalQuiz: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(finalQuiz) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
