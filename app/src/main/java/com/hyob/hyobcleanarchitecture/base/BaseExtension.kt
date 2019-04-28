package com.hyob.hyobcleanarchitecture.base

import android.view.View
import android.widget.TextView
import androidx.lifecycle.LiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

operator fun CompositeDisposable.plusAssign(subscribe: Disposable) {
    this.add(subscribe)
}

fun TextView.string() = this.text.toString()

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}
