package com.example.spacex.presentation.di.modules

import com.example.spacex.data.ApiService
import com.example.spacex.data.RepositoryImpl
import com.example.spacex.domain.IRepository
import com.example.spacex.presentation.di.modules.NetworkModule
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
object ReposModule {
    @Provides
    fun provideRepository(apiService: ApiService): RepositoryImpl =
        RepositoryImpl(apiService)
}