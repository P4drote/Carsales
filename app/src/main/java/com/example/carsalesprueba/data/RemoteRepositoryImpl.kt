package com.example.carsalesprueba.data

import com.example.carsalesprueba.model.Statistic
import com.example.carsalesprueba.util.Resource
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val dataSource: RemoteDataSource) :
    RemoteRepository {
    override suspend fun getCoronavirusInformation(date: String): Resource<Statistic> {
        return dataSource.getCoronavirusInformation(date)
    }
}
