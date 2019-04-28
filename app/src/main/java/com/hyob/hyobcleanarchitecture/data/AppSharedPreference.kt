package com.hyob.hyobcleanarchitecture.data

import android.content.Context
import android.content.SharedPreferences

object AppSharedPreference {

    private const val KEY_APP_SHARED_PREF = "app_shared_preference"
    private lateinit var sharedPreferences: SharedPreferences

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(KEY_APP_SHARED_PREF, Context.MODE_PRIVATE)
    }

    fun getSharedPref() = sharedPreferences

}