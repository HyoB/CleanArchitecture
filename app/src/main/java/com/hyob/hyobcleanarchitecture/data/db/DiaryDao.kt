package com.hyob.hyobcleanarchitecture.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface DiaryDao {

    @Insert(onConflict = REPLACE)
    fun insert(diaryDto: DiaryDto): Completable

    @Query("SELECT * FROM $DIARY_CONTENT_TABLE_NAME")
    fun getAllDiaries(): Single<List<DiaryDto>>

    @Query("SELECT * FROM $DIARY_CONTENT_TABLE_NAME WHERE id = :id")
    fun getDiaryById(id: Long): Single<DiaryDto>

    @Query("DELETE FROM $DIARY_CONTENT_TABLE_NAME WHERE id = :id")
    fun deleteDiary(id: Long): Completable

}