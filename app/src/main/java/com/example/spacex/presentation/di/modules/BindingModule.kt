package com.example.spacex.presentation.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
interface BindingModule {
    @Binds
    fun context(appInstance: Application): Context
}