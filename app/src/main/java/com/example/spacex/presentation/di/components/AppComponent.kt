package com.example.spacex.presentation.di.components

import android.app.Application
import com.example.spacex.presentation.rocket.RocketFragment
import com.example.spacex.presentation.di.modules.BindingModule
import com.example.spacex.presentation.di.modules.NetworkModule
import com.example.spacex.presentation.di.modules.ReposModule
import com.example.spacex.presentation.di.modules.VMModule
import com.example.spacex.presentation.home.HomeFragment
import com.example.spacex.presentation.notifications.NotificationsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, VMModule::class, BindingModule::class, ReposModule::class])
@Singleton
interface ApplicationComponent {
    fun inject(activity: HomeFragment)
    fun inject(activity: RocketFragment)
    fun inject(activity: NotificationsFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}