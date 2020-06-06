package com.example.notesappp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class),version = 4)
abstract class NoteDatabase:RoomDatabase(){
    abstract fun noteDao():NoteDao

    companion object {
        @Volatile   // indicates our app to always refer to its instance from the memory
        var database:NoteDatabase? = null
        fun getInstance(context: Context):NoteDatabase?{
            if(database == null){
                synchronized(NoteDatabase::class.java){
                    if(database == null){
                        database = Room.databaseBuilder(context.applicationContext,NoteDatabase::class.java,"note_datavase").fallbackToDestructiveMigration().build()
                    }
                }
            }
            return database
        }
    }
}