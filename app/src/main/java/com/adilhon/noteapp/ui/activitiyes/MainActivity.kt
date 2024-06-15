package com.adilhon.noteapp.ui.activitiyes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.adilhon.noteapp.R
import com.adilhon.noteapp.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =  supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        val preference = PreferenceHelper()
        preference.unit(this)

        val check = preference.saveBoolean

        if(check == false){
            navController.navigate(R.id.onBoardFragment)
        }else{
            navController.navigate(R.id.noteFragment)
        }
    }
}