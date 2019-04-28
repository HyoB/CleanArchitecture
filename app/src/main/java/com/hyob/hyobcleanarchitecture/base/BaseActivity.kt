package com.hyob.hyobcleanarchitecture.base

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    val vm by lazy { createVM() }

    abstract fun createVM(): T

    fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    fun <V>LiveData<V>.observe(observer: (V) -> Unit) {
        this.observe({lifecycle}, observer)
    }

    fun startActivity(target: Class<*>, vararg flags: Int) {
        startActivity(
            Intent(this, target).apply {
                flags.forEach { addFlags(it) }
            }
        )
    }
}