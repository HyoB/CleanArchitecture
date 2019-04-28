package com.hyob.hyobcleanarchitecture.data.db

import com.hyob.hyobcleanarchitecture.base.BaseRepository
import com.hyob.hyobcleanarchitecture.entity.Diary
import io.reactivex.Completable
import io.reactivex.Single

interface DiaryRepository: BaseRepository {

    fun createDiaryOnDb(title: String, content: String): Completable

    fun readAllDiaryOnDb(): Single<List<Diary>>

    fun readDiaeryById(id: Long): Single<Diary>

    fun deleteDiaryOnDb(id: Long): Completable

    fun createDiaryTest(diaryTitle: String, diaryContent: String): (String, String) -> Completable

    fun updateDiary()

}