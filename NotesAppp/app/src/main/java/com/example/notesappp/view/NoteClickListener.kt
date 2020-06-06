package com.example.notesappp.view

import com.example.notesappp.model.Note

interface NoteClickListener {
    fun onClick(note: Note)
}