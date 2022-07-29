package com.example.spacex.presentation.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spacex.presentation.VMFactory
import com.example.spacex.presentation.di.VMKey
import com.example.spacex.presentation.rockets.RocketsViewModel
import com.example.spacex.presentation.launches.LaunchesViewModel
import com.example.spacex.presentation.launchpads.LaunchpadsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VMModule {
    @Binds
    @IntoMap
    @VMKey(RocketsViewModel::class)
    abstract fun bindVM(viewModel: RocketsViewModel): ViewModel

    @Binds
    @IntoMap
    @VMKey(LaunchesViewModel::class)
    abstract fun bindVMProfile(viewModel: LaunchesViewModel): ViewModel

    @Binds
    @IntoMap
    @VMKey(LaunchpadsViewModel::class)
    abstract fun bindVMCart(viewModel: LaunchpadsViewModel): ViewModel

    @Binds
    abstract fun bindVMFactory(factory: VMFactory): ViewModelProvider.Factory
}
