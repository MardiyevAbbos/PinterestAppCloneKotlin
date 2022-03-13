package com.example.pinterestappkotlin.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_photos")
data class PhotoRoom(
    @PrimaryKey val id: String,
    var photo: String? = null,
    var description: String? = null,
    var color: String? = null
)
