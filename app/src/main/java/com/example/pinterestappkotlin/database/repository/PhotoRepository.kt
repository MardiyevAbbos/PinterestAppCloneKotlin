package com.example.pinterestappkotlin.database.repository

import android.app.Application
import com.example.pinterestappkotlin.database.AppDatabase
import com.example.pinterestappkotlin.database.dao.PhotoDao
import com.example.pinterestappkotlin.database.entity.PhotoRoom

class PhotoRepository(application: Application) {
    private var photoDao:PhotoDao

    init {
        val db: AppDatabase = AppDatabase.getInstance(application)
        photoDao = db.photoDao
    }

    fun savePhoto(photoRoom: PhotoRoom) {
        photoDao.insertPhoto(photoRoom)
    }

    fun getAllPhotos(): List<PhotoRoom> {
        return photoDao.getPhotoList()
    }

    fun clearNotes() {
        photoDao.clearPhotos()
    }

}