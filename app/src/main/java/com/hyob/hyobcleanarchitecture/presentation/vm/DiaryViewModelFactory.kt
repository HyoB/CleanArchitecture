package com.hyob.hyobcleanarchitecture.presentation.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hyob.hyobcleanarchitecture.data.db.DiaryDatabase
import com.hyob.hyobcleanarchitecture.data.db.DiaryRepositoryImpl

@Suppress("UNCHECKED_CAST")
class DiaryViewModelFactory(val context: Context): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val diaryDb = DiaryDatabase.getInstance(context)
        return DiaryViewModel(DiaryRepositoryImpl(diaryDb)) as T
    }

}