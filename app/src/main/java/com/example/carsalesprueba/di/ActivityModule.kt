package com.example.carsalesprueba.di

import com.example.carsalesprueba.data.RemoteDataSource
import com.example.carsalesprueba.data.RemoteDataSourceImpl
import com.example.carsalesprueba.data.RemoteRepository
import com.example.carsalesprueba.data.RemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindRemoteRepositoryImpl(repositoryImpl: RemoteRepositoryImpl): RemoteRepository

    @Binds
    abstract fun bindRemoteDataSourceImpl(dataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

}