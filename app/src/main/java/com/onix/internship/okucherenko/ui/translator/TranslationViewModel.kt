package com.onix.internship.okucherenko.ui.translator

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.domain.entity.Dictionary
import com.onix.internship.okucherenko.domain.entity.DictionaryItem
import com.onix.internship.okucherenko.domain.entity.ResultTranslate

class TranslationViewModel : BaseViewModel() {

    lateinit var currentContext: Context
    var filtered = listOf<DictionaryItem>()
    var resultCollection = mutableListOf<ResultTranslate>()

    private val _translationList = MutableLiveData<MutableList<ResultTranslate>>()
    val translationList: LiveData<MutableList<ResultTranslate>>
        get() = _translationList

    var directTranslation = false
    var reverseTranslation = false
    var word = ""
    private var resultTranslateList = mutableListOf<ResultTranslate>()

    init {

        if(!directTranslation && !reverseTranslation) {
            directTranslation = true
        }
    }

    fun onTranslate() {
        filtered = listOf()
        resultCollection = mutableListOf()
        var result = ""
        if (reverseTranslation) {
            filtered = Dictionary.dictionary.filter { it.wordLang2 == word.uppercase() }
            filtered.forEach{
                resultCollection.add(ResultTranslate(it.wordLang2, it.wordLang1))
                result += "${it.wordLang1} "
            }
        } else {
            filtered = Dictionary.dictionary.filter { it.wordLang1 == word.uppercase() }
            filtered.forEach{
                resultCollection.add(ResultTranslate(it.wordLang1, it.wordLang2))
                result += "${it.wordLang2} "
            }
        }

        result.trim()
        if(filtered.isEmpty()) {
            Toast.makeText(currentContext, currentContext.getString(R.string.translation_is_missing), Toast.LENGTH_SHORT).show()
            return
        }

        val resultTranslate = ResultTranslate(word, result)

        resultTranslateList.remove(resultTranslate)
        resultTranslateList.add(resultTranslate)
        _translationList.value = resultTranslateList.takeLast(16) as MutableList<ResultTranslate>
    }
}