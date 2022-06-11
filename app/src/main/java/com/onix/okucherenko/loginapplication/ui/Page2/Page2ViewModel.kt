package com.onix.okucherenko.loginapplication.ui.Page2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.onix.okucherenko.loginapplication.model.quiz.Quiz

class Page2ViewModel(finalQuiz: String): ViewModel() {
    var quiz: Quiz = Gson().fromJson(finalQuiz, Quiz::class.java)
    var actualPage = quiz.page[1].number - 1
    lateinit var modelQuiz: String

    fun onClickButtonPage2toPage3() {
        modelQuiz = Gson().toJson(quiz)
    }

}

class Page2ViewModelFactory(private val finalQuiz: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Page2ViewModel::class.java)) {
            return Page2ViewModel(finalQuiz) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}