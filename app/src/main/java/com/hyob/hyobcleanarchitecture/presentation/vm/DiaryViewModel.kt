package com.hyob.hyobcleanarchitecture.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hyob.hyobcleanarchitecture.base.BaseViewModel
import com.hyob.hyobcleanarchitecture.base.plusAssign
import com.hyob.hyobcleanarchitecture.domain.DiaryNullPointException
import com.hyob.hyobcleanarchitecture.data.db.DiaryRepository
import com.hyob.hyobcleanarchitecture.domain.DiaryUseCase
import com.hyob.hyobcleanarchitecture.presentation.model.DiaryViewData

class DiaryViewModel(private val diaryRepository: DiaryRepository) : BaseViewModel(), DiaryUseCase {

    private val diaryContents = MutableLiveData<List<DiaryViewData>>()
    private val diaryCreated = MutableLiveData<Unit>()
    private val diaryDeleteCheckBox = MutableLiveData<Boolean>()

    override fun getRepository(): DiaryRepository = diaryRepository

    init {
        getAllDiaries()
    }

    fun getAllDiaries() {
        disposables += showDiaryList()
            .subscribe({
                diaryContents.postValue(it)
            }, {

            })
    }

    fun createNewDiary(title: String, content: String) {
        try {
            disposables += writeNewDiary(title, content)
                .subscribe {
                    diaryCreated.postValue(Unit)
                }
        } catch (e: DiaryNullPointException) {
            toastText.postValue(e.type.reason)
        }
    }

    fun showDiaryDeleteCheckBox(show: Boolean) {
        diaryDeleteCheckBox.postValue(show)
    }

    fun getDiaryContents(): LiveData<List<DiaryViewData>> = diaryContents
    fun getDiaryCreated(): LiveData<Unit> = diaryCreated
    fun getDiaryDeleteCheckBox(): LiveData<Boolean> = diaryDeleteCheckBox


}


