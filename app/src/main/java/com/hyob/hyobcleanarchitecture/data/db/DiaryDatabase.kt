package com.hyob.hyobcleanarchitecture.data.db

import android.content.Context
import androidx.room.*

@Database(entities = [DiaryDto::class], version = 1, exportSchema = false)
abstract class DiaryDatabase : RoomDatabase(){

    abstract fun getDao() : DiaryDao

    companion object {
        private var instance : DiaryDatabase? = null

        fun getInstance(context: Context): DiaryDatabase {
            if (instance == null) {
                synchronized(DiaryDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        DiaryDatabase::class.java,
                        APP_DATA_BASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance!!
        }

    }

}