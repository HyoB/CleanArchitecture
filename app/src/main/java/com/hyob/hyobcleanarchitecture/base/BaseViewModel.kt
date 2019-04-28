package com.hyob.hyobcleanarchitecture.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {

    val disposables = CompositeDisposable()

    protected val toastText = MutableLiveData<String>()

    fun getToastContent() = toastText

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}