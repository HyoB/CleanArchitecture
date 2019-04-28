package com.hyob.hyobcleanarchitecture.base

import android.app.Application
import com.hyob.hyobcleanarchitecture.data.AppSharedPreference

class HyobApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AppSharedPreference.initialize(this)
    }

}