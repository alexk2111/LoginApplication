package com.onix.internship.okucherenko.ui.listoftranslations

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.onix.internship.okucherenko.domain.entity.ResultTranslate

class ListoftranslationsFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(resultToString())
            .setPositiveButton(getString(android.R.string.ok)) { _, _ -> }
            .create()

    private fun resultToString(): String {
        var result = ""
        resultCollection.forEach {
            result += "${it.word} - ${it.translate}\n"
        }
        return result
    }

    companion object {
        const val TAG = "Result translate"
        var resultCollection = listOf<ResultTranslate>()
    }
}