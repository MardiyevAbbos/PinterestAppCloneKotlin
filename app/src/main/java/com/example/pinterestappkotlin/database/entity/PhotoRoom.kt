package com.example.pinterestappkotlin.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pinterestappkotlin.model.PhotoItem

@Entity(tableName = "table_photos")
data class PhotoRoom(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var photoItem: PhotoItem
)
