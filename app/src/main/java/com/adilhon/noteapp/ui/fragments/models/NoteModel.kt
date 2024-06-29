package com.adilhon.noteapp.ui.fragments.models

import android.provider.ContactsContract.RawContacts.Data
import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serial
import java.io.Serializable

@Entity(tableName = "note")
data class NoteModel(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val description: String,
    val time: String,
    val containerColor: String
): Serializable
