package com.example.spacex.data

import com.example.spacex.data.model.Launch
import com.example.spacex.data.model.Launchpad
import com.example.spacex.data.model.Rocket
import com.example.spacex.domain.IRepository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : IRepository {

    override suspend fun getListOfRockets(): ArrayList<Rocket>? {
        return apiService.getApiRocket().body()
    }

    override suspend fun getListOfLaunches(): ArrayList<Launch>? {
        return apiService.getApiLaunches().body()
    }

    override suspend fun getListOfLaunchpads(): ArrayList<Launchpad>? {
        return apiService.getApiLaunchpads().body()
    }


}