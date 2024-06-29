package com.adilhon.noteapp

import android.app.Application
import androidx.room.Room
import com.adilhon.noteapp.data.room.database.NoteDatabase

class App: Application() {

    companion object {
        lateinit var noteDatabase: NoteDatabase
    }

    override fun onCreate() {
        super.onCreate()
        noteDatabase = Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            "our-database"
        ).allowMainThreadQueries().build()
    }
}