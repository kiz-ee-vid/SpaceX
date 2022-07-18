package com.example.spacex.data

import com.example.spacex.data.model.Rocket
import com.example.spacex.domain.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET(Constants.ROCKETS_ENDPOINT)
    suspend fun getApiRocket(): Response<ArrayList<Rocket>>

}