package com.example.notesappp.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class NoteRepository(application: Application){
    private val noteDao:NoteDao?
    private val allNotes:LiveData<List<Note>>?
    init {
        val db = NoteDatabase.getInstance(application)
        noteDao = db?.noteDao()
        allNotes = noteDao?.getAll()
    }
    fun insert(note:Note){
        InsertAsyncTask(noteDao!!).execute(note)
    }
    fun delete(note:Note){
        DeleteAsyncTask(noteDao!!).execute(note)
    }
    fun update(note:Note){
        UpdateAsyncTask(noteDao!!).execute(note)
    }
    private class UpdateAsyncTask(private val dao:NoteDao):AsyncTask<Note,Void,Void>(){
        override fun doInBackground(vararg params: Note): Void? {
            dao.update(params[0])
            return null
        }
    }
    private class DeleteAsyncTask(private  val dao: NoteDao):AsyncTask<Note,Void,Void>(){
        override fun doInBackground(vararg params: Note): Void? {
            dao.delete(params[0])
            return null
        }
    }

    private class InsertAsyncTask(private val dao:NoteDao):AsyncTask<Note,Void,Void>()
    {
        override fun doInBackground(vararg params: Note): Void? {
            dao.insert(params[0])
            return null
        }
    }
    fun getAll():LiveData<List<Note>>{
        return allNotes!!
    }
}