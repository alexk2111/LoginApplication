package com.onix.internship.okucherenko.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")

data class NoteItem(
    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0L,

    @ColumnInfo(name = "date_milli")
    val date: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "color")
    var color: Int,

    @ColumnInfo(name = "content")
    var content: String,

    @ColumnInfo(name = "editable")
    var editable: Boolean
    )
