package com.example.pinterestappkotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pinterestappkotlin.database.dao.PhotoDao
import com.example.pinterestappkotlin.database.entity.PhotoRoom
import com.example.pinterestappkotlin.database.converter.PhotoTypeConverter

@Database(entities = [PhotoRoom::class], version = 2)
@TypeConverters(PhotoTypeConverter::class) // for convert from Object to String
abstract class AppDatabase : RoomDatabase(){

    abstract val photoDao:PhotoDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "app.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries() // this is for to allow (get,set,delete,remove) data in Room Database, Where in Main Thread
                    .build()
            }

            return instance!!
        }
    }


}