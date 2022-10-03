package com.pseedk.nononotes.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.pseedk.nononotes.database.firebase.AppFirebaseRepository
import com.pseedk.nononotes.database.room.AppRoomDatabase
import com.pseedk.nononotes.database.room.AppRoomRepository
import com.pseedk.nononotes.utilits.*

class StartFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application

    fun initDatabase(type: String, onSuccess:  () -> Unit) {
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()

            }

            TYPE_FIREBASE -> {
                REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToDatabase({onSuccess()}, { showSnackbar(it, VIEW_FOR_SNACKBAR) })
            }
        }
    }
}