package com.educode.logincompose.di

import com.educode.logincompose.data.service.ReqresService
import com.educode.logincompose.data.service.ReqresServiceDataSource
import com.educode.logincompose.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun bindRemoteDataSource(service: ReqresService): RemoteDataSource {
        return ReqresServiceDataSource(service)
    }
}