package com.example.notesappp.model


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    fun insert(note: Note)

    @Query("SELECT * FROM Note")
    fun getAll(): LiveData<List<Note>>

    @Delete
    fun delete(note: Note)

    @Update
    fun update(note: Note)

}