package com.hyob.hyobcleanarchitecture.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hyob.hyobcleanarchitecture.base.BaseViewModel
import com.hyob.hyobcleanarchitecture.base.plusAssign
import com.hyob.hyobcleanarchitecture.data.db.DiaryRepository
import com.hyob.hyobcleanarchitecture.domain.DiaryUseCase
import com.hyob.hyobcleanarchitecture.presentation.model.DiaryViewData

class DiaryContentViewModel(
    private val diaryId: Long,
    private val diaryRepository: DiaryRepository) : BaseViewModel(), DiaryUseCase {

    private val diaryDetail = MutableLiveData<DiaryViewData>()

    override fun getRepository(): DiaryRepository = diaryRepository

    init {
        disposables += getDiaryById(diaryId)
            .subscribe({
                diaryDetail.postValue(it)
            }, {

            })
    }

    fun getDiaryDetail(): LiveData<DiaryViewData> = diaryDetail

}


