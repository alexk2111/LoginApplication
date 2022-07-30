package com.onix.internship.okucherenko.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDatabaseDao {

    @Insert
    suspend fun insert(noteItem: NoteItem)

    @Update
    suspend fun update(noteItem: NoteItem)

    @Query("DELETE FROM note_table")
    suspend fun clear()

    @Query(
        "SELECT * FROM note_table ORDER BY CASE WHEN :isAsc = '0' THEN date_milli END ASC, " +
                "CASE WHEN :isAsc = '1' THEN date_milli END DESC, " +
                "CASE WHEN :isAsc = '2' THEN title END ASC, " +
                "CASE WHEN :isAsc = '3' THEN title END DESC, " +
                "CASE WHEN :isAsc = '4' THEN color END ASC, " +
                "CASE WHEN :isAsc = '5' THEN color END DESC, " +
                "CASE WHEN :isAsc = '6' THEN content END ASC, " +
                "CASE WHEN :isAsc = '7' THEN content END DESC"

    )
    fun getAll(isAsc: Int): LiveData<List<NoteItem>>

//    @Query("SELECT * FROM note_table ORDER BY date_milli")
//    fun getAllOrderDate(): LiveData<List<NoteItem>>
//
//    @Query("SELECT * FROM note_table ORDER BY date_milli DESC")
//    fun getAllOrderDateDesc(): LiveData<List<NoteItem>>
//
//    @Query("SELECT * FROM note_table ORDER BY title")
//    fun getAllOrderTitle(): MutableLiveData<List<NoteItem>>
//
//    @Query("SELECT * FROM note_table ORDER BY title DESC")
//    fun getAllOrderTitleDesc(): MutableLiveData<List<NoteItem>>
//
//    @Query("SELECT * FROM note_table ORDER BY content")
//    fun getAllOrderContent(): LiveData<List<NoteItem>>
//
//    @Query("SELECT * FROM note_table ORDER BY content DESC")
//    fun getAllOrderContentDesc(): LiveData<List<NoteItem>>
//
//    @Query("SELECT * FROM note_table ORDER BY color")
//    fun getAllOrderColor(): LiveData<List<NoteItem>>
//
//    @Query("SELECT * FROM note_table ORDER BY color DESC")
//    fun getAllOrderColorDesc(): LiveData<List<NoteItem>>
}