package com.pseedk.nononotes.database.room

import androidx.lifecycle.LiveData
import com.pseedk.nononotes.database.DatabaseRepository
import com.pseedk.nononotes.models.AppNote

class AppRoomRepository(private val appRoomDao: AppRoomDao): DatabaseRepository {
    override val allNotes: LiveData<List<AppNote>>
        get() = appRoomDao.getAllNotes()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.insert(note)
        onSuccess()
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.delete(note)
        onSuccess()
    }

    override fun signOut() {
        super.signOut()
    }
}