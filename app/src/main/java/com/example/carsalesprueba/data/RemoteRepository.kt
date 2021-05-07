package com.example.carsalesprueba.data

import com.example.carsalesprueba.model.Statistic
import com.example.carsalesprueba.util.Resource

interface RemoteRepository {
    suspend fun getCoronavirusInformation(date: String): Resource<Statistic>
}
