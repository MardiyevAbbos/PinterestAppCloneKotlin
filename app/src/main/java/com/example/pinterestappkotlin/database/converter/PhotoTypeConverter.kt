package com.example.pinterestappkotlin.database.converter

import androidx.room.TypeConverter
import com.example.pinterestappkotlin.model.PhotoItem
import com.google.gson.Gson

class PhotoTypeConverter {
    @TypeConverter
    fun fromPhotoItem(photoItem: PhotoItem): String {
        return Gson().toJson(photoItem)
    }

    @TypeConverter
    fun toPhotoItem(json: String): PhotoItem {
        return Gson().fromJson(json, PhotoItem::class.java)
    }
}