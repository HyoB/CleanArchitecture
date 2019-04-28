package com.hyob.hyobcleanarchitecture.presentation.vm

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hyob.hyobcleanarchitecture.data.db.DiaryDatabase
import com.hyob.hyobcleanarchitecture.data.db.DiaryRepositoryImpl
import com.hyob.hyobcleanarchitecture.presentation.view.DiaryDetailActivity

@Suppress("UNCHECKED_CAST")
class DiaryContentViewModelFactory(val activity: Activity): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val diaryId = activity.intent.getLongExtra(DiaryDetailActivity.KEY_DIARY_ID, -1)
        val diaryDb = DiaryDatabase.getInstance(activity)
        return DiaryContentViewModel(diaryId, DiaryRepositoryImpl(diaryDb)) as T
    }

}