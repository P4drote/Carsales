package com.example.carsalesprueba.data

import com.example.carsalesprueba.domain.WebService
import com.example.carsalesprueba.model.Statistic
import com.example.carsalesprueba.util.Resource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val webService: WebService) :
    RemoteDataSource {
    override suspend fun getCoronavirusInformation(date: String): Resource<List<Statistic>> {
        return webService.getCoronavirusInformation()
    }
}