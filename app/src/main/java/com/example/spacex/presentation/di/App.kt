package com.example.spacex.presentation.di

import android.app.Application
import android.content.Context
import com.example.spacex.presentation.di.components.ApplicationComponent
import com.example.spacex.presentation.di.components.DaggerApplicationComponent

class App : Application() {
    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build()
    }

}
val Context.appComponent: ApplicationComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }