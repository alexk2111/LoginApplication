package com.onix.internship.okucherenko.data.repository

import androidx.lifecycle.LiveData
import com.onix.internship.okucherenko.data.database.NoteDatabase
import com.onix.internship.okucherenko.data.database.NoteItem

class NoteRepository(private val database: NoteDatabase) {

    var allNotes: LiveData<List<NoteItem>>

    init {
        allNotes = database.noteDatabaseDao.getAll(1)
    }


fun getAll(isAsc: Int) {
    allNotes = database.noteDatabaseDao.getAll(isAsc)
}
}