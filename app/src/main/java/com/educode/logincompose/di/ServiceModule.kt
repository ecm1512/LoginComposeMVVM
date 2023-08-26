package com.educode.logincompose.di

import com.educode.logincompose.data.service.ReqresService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideReqresService() = Retrofit.Builder()
        .baseUrl("https://reqres.in/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ReqresService::class.java)
}