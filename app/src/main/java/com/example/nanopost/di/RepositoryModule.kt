package com.example.nanopost.di

import com.example.nanopost.data.repository.PrefsRepository
import com.example.nanopost.data.repository.ApiRepository
import com.example.nanopost.data.repository.AuthRepository
import com.example.nanopost.data.repository.impl.ApiRepositoryImpl
import com.example.nanopost.data.repository.impl.AuthRepositoryImpl
import com.example.nanopost.data.repository.impl.PrefsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindApiRepository(repositoryImpl: ApiRepositoryImpl) : ApiRepository

    @Binds
    abstract fun bindAuthRepository(repositoryImpl: AuthRepositoryImpl) : AuthRepository

    @Binds
    abstract fun bindPrefsRepository(repositoryImpl: PrefsRepositoryImpl) : PrefsRepository
}