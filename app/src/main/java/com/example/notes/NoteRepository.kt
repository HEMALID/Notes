package com.example.notes

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(Note: Note){
        noteDao.insert(Note)
    }
    suspend fun delete(Note: Note){
        noteDao.delete(Note)
    }
}
