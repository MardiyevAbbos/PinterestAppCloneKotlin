package com.example.pinterestappkotlin.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pinterestappkotlin.database.entity.PhotoRoom

@Dao
interface PhotoDao {

    @Insert()
    fun insertPhoto(photoRoom: PhotoRoom)

    @Query("SELECT * FROM table_photos")
    fun getPhotoList(): List<PhotoRoom>

    @Query("SELECT * FROM table_photos WHERE id=:photoId")
    fun getPhoto(photoId: Int): PhotoRoom

    @Query("DELETE FROM table_photos")
    fun clearPhotos()

}