package com.educode.logincompose.di

import com.educode.logincompose.data.repository.AuthRepositoryImpl
import com.educode.logincompose.data.repository.UserDataMapper
import com.educode.logincompose.data.repository.UserRepositoryImpl
import com.educode.logincompose.data.source.RemoteDataSource
import com.educode.logincompose.domain.repository.AuthRepository
import com.educode.logincompose.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun bindAuthRepository(
        remoteDataSource: RemoteDataSource
    ): AuthRepository {
        return AuthRepositoryImpl(remoteDataSource)
    }

    @Provides
    fun bindUserRepository(
        remoteDataSource: RemoteDataSource,
        mapper: UserDataMapper
    ): UserRepository {
        return UserRepositoryImpl(remoteDataSource, mapper)
    }
}