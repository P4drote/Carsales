package com.example.carsalesprueba.domain

import com.example.carsalesprueba.model.AllData
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("total?date=")
    suspend fun getCoronavirusInformation(@Query("date") date: String): AllData

}