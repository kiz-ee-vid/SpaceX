package com.example.spacex.domain

import com.example.spacex.data.model.Rocket
import retrofit2.Response

interface IRepository {
    suspend fun getListOfRockets(): ArrayList<Rocket>?

}