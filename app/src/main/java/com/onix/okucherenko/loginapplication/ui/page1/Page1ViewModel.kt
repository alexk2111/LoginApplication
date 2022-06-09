package com.onix.okucherenko.loginapplication.ui.page1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.onix.okucherenko.loginapplication.model.quiz.Answer
import com.onix.okucherenko.loginapplication.model.quiz.Quiz

class Page1ViewModel(finalQuiz: String): ViewModel() {

    var quiz: Quiz = Gson().fromJson(finalQuiz, Quiz::class.java)
    var actualPage = quiz.page[0].number - 1
    lateinit var modelQuiz: String
    var answers: MutableList<Answer> = mutableListOf()



    fun onClickButtonPage1toPage2() {
        modelQuiz = Gson().toJson(quiz)
    }

}

class Page1ViewModelFactory(private val finalQuiz: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Page1ViewModel::class.java)) {
            return Page1ViewModel(finalQuiz) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}