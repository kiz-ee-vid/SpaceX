package com.example.spacex.data

import com.example.spacex.data.model.Launch
import com.example.spacex.data.model.Rocket
import com.example.spacex.domain.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.ROCKETS_ENDPOINT)
    suspend fun getApiRocket(): Response<ArrayList<Rocket>>

    @GET(Constants.LAUNCHES_ENDPOINT)
    suspend fun getApiLaunches(): Response<ArrayList<Launch>>
}

