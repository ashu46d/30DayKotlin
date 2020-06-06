package com.example.notesappp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Note")
class Note(
    @ColumnInfo(name = "noteHeading")
    var noteHeading: String = "",
    @ColumnInfo(name = "ntoeBody")
    var noteBody: String = "",
    @ColumnInfo(name = "noteColor")
    var noteColor: String = "#ffffffff"
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}
