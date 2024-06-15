package com.adilhon.noteapp.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper {
    private lateinit var sharedPreference: SharedPreferences

    fun unit(context: Context){
        sharedPreference = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }

    var text: String?
        get() = sharedPreference.getString("text", "null")
        set(value) = sharedPreference.edit().putString("text", value)!!.apply()

    var isOnBoardShow : Boolean
        get() = sharedPreference.getBoolean("board", true)
        set(value) = sharedPreference.edit().putBoolean("board", value).apply()

    var saveBoolean
        set(value) = sharedPreference.edit()?.putBoolean("Bool", value)?.apply()!!
        get() = sharedPreference.getBoolean("Bool", false)
}