package com.example.carsalesprueba.domain

import com.example.carsalesprueba.SettingBody
import com.example.carsalesprueba.model.Statistic
import com.example.carsalesprueba.util.Resource
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers

interface WebService {

    @Headers("X-RapidAPI-Key", "96afa298cbmsh913f910f914494cp110c39jsn01a32d68445e")
    @GET("total")
    suspend fun getCoronavirusInformation(@Body body: SettingBody): Resource<List<Statistic>>
}