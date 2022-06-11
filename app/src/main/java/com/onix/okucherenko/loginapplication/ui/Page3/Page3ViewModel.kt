package com.onix.okucherenko.loginapplication.ui.Page3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.onix.okucherenko.loginapplication.model.quiz.Quiz

class Page3ViewModel(finalQuiz: String): ViewModel() {
    var quiz: Quiz = Gson().fromJson(finalQuiz, Quiz::class.java)
    var actualPage = quiz.page[2].number - 1
    lateinit var modelQuiz: String

    fun onClickButtonPage3toResult() {
        modelQuiz = Gson().toJson(quiz)
    }

}

class Page3ViewModelFactory(private val finalQuiz: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Page3ViewModel::class.java)) {
            return Page3ViewModel(finalQuiz) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
