package com.example.interesting_facts_about_numbers.app

import android.app.Application
import com.example.interesting_facts_about_numbers.functionality.data.db.DataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    private val database by lazy { DataBase.getInstance(this) }
}