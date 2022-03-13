package com.example.pinterestappkotlin.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_photos")
data class PhotoRoom(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var photo: String,
    var photoId: String
)
