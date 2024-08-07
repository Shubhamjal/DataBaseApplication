package com.example.databaseapplication

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var Title:String?="",
    var Description:String?="",
)
