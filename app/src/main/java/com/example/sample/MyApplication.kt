package com.example.sample

import android.app.Application
import com.example.sample.di.AppComponent
import com.example.sample.di.DaggerAppComponent

class MyApplication:Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent=DaggerAppComponent.builder().build()
    }
}