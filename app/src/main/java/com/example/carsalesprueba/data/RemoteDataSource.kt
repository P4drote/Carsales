package com.example.carsalesprueba.data

import com.example.carsalesprueba.model.Statistic
import com.example.carsalesprueba.util.Resource

interface RemoteDataSource {
    suspend fun getCoronavirusInformation(date: String): Resource<List<Statistic>>
}