package com.onix.internship.okucherenko.ui.notelist

import android.content.Context
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.data.database.NoteDatabase
import com.onix.internship.okucherenko.data.repository.NoteRepository

class NoteViewModel(private val context: Context) : BaseViewModel() {
    private val application = context.applicationContext
    private val noteRepository = NoteRepository(NoteDatabase.getInstance(application))
    val notes = noteRepository.allNotes

    fun sort(isAsc: Int) {
        noteRepository.getAll(isAsc)
    }
}