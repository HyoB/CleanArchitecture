package com.hyob.hyobcleanarchitecture.domain

import com.hyob.hyobcleanarchitecture.base.RepositoryProvider
import com.hyob.hyobcleanarchitecture.data.db.DiaryRepository
import com.hyob.hyobcleanarchitecture.presentation.model.DiaryViewData
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface DiaryUseCase : RepositoryProvider<DiaryRepository> {

    fun showDiaryList(): Single<List<DiaryViewData>> =
        getRepository().readAllDiaryOnDb()
            .subscribeOn(Schedulers.io())
            .map { it.map { DiaryEntityMapper.mapEntityToView(it) } }

    fun writeNewDiary(title: String, content: String): Completable {
        if (title.isNullOrEmpty())
            throw DiaryNullPointException(DiaryNullType.TITLE)
        if (content.isNullOrEmpty())
            throw DiaryNullPointException(DiaryNullType.CONTENT)
        return getRepository().createDiaryOnDb(title, content)
    }

    fun getDiaryById(id: Long) =
        getRepository().readDiaeryById(id)
            .subscribeOn(Schedulers.io())
            .map { DiaryEntityMapper.mapEntityToView(it) }

    fun deleteDiary(id: Long) =
        getRepository().deleteDiaryOnDb(id)
            .subscribeOn(Schedulers.io())

}