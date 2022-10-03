package com.pseedk.nononotes.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.pseedk.nononotes.utilits.REPOSITORY

class MainFragmentViewModel(application: Application): AndroidViewModel(application) {

    val allNotes = REPOSITORY.allNotes

    fun signOut() {
        REPOSITORY.signOut()
    }
}