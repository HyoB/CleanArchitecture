package com.hyob.hyobcleanarchitecture.data.db

import com.hyob.hyobcleanarchitecture.domain.DiaryEntityMapper
import com.hyob.hyobcleanarchitecture.entity.Diary
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DiaryRepositoryImpl(private val diaryDb: DiaryDatabase) : DiaryRepository {
    override fun readAllDiaryOnDb(): Single<List<Diary>> =
        diaryDb.getDao().getAllDiaries()
            .subscribeOn(Schedulers.io())
            .map { it.map { DiaryEntityMapper.mapDataToEntity(it) } }

    override fun createDiaryOnDb(title: String, content: String): Completable =
        diaryDb.getDao().insert(DiaryDto(null, title, content))
            .subscribeOn(Schedulers.io())

    override fun readDiaeryById(id: Long): Single<Diary> =
        diaryDb.getDao().getDiaryById(id)
            .subscribeOn(Schedulers.io())
            .map { DiaryEntityMapper.mapDataToEntity(it) }

    override fun deleteDiaryOnDb(id: Long): Completable =
        diaryDb.getDao().deleteDiary(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun updateDiary() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createDiaryTest(diaryTitle: String, diaryContent: String): (String, String) -> Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}