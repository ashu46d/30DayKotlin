package com.example.notesappp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notesappp.model.Note
import com.example.notesappp.model.NoteRepository

class NoteViewModel(application: Application):AndroidViewModel(application) {
    private val noteRepository: NoteRepository
    private val allNotes:LiveData<List<Note>>

    init {
        noteRepository = NoteRepository(application)
        allNotes = noteRepository.getAll()
    }
    fun insertNote(note:Note){
        noteRepository.insert(note)
    }
    fun getAll():LiveData<List<Note>>{
        return allNotes
    }
    fun delete(note:Note){
        noteRepository.delete(note)
    }
    fun update(note:Note){
        noteRepository.update(note)
    }
}