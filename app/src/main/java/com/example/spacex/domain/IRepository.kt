package com.example.spacex.domain

import com.example.spacex.data.model.Rocket

interface IRepository {
    suspend fun getListOfRockets(): ArrayList<Rocket>?

}