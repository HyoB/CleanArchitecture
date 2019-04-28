package com.hyob.hyobcleanarchitecture.base

import androidx.room.RoomDatabase

interface RepositoryProvider<T: BaseRepository> {

    fun getRepository(): T

}