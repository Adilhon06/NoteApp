package com.adilhon.noteapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.adilhon.noteapp.ui.fragments.models.NoteModel

@Dao
interface NoteDao {

    @Insert
    fun insertNote(note: NoteModel)

    @Query("SELECT * FROM note")
    fun getAllNotes(): List<NoteModel>

    @Update
    fun updateNote(note: NoteModel)
}