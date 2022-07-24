package com.example.spacex.presentation.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spacex.presentation.VMFactory
import com.example.spacex.presentation.di.VMKey
import com.example.spacex.presentation.home.HomeViewModel
import com.example.spacex.presentation.launches.LaunchesViewModel
import com.example.spacex.presentation.notifications.NotificationsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VMModule {
    @Binds
    @IntoMap
    @VMKey(HomeViewModel::class)
    abstract fun bindVM(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @VMKey(LaunchesViewModel::class)
    abstract fun bindVMProfile(viewModel: LaunchesViewModel): ViewModel

    @Binds
    @IntoMap
    @VMKey(NotificationsViewModel::class)
    abstract fun bindVMCart(viewModel: NotificationsViewModel): ViewModel

    @Binds
    abstract fun bindVMFactory(factory: VMFactory): ViewModelProvider.Factory
}
