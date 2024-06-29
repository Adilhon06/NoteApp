package com.adilhon.noteapp.data.room.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adilhon.noteapp.data.room.dao.NoteDao
import com.adilhon.noteapp.ui.fragments.models.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}