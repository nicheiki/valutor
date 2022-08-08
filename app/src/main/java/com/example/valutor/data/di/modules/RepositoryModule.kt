package com.example.valutor.data.di.modules

import com.example.valutor.data.repositories.LocalRepositoryImpl
import com.example.valutor.data.repositories.RemoteRepositoryImpl
import com.example.valutor.domain.repositories.LocalRepository
import com.example.valutor.domain.repositories.RemoteRepository
import com.example.valutor.domain.usecases.RatesStreamUseCaseImpl
import com.example.valutor.presentation.viewModels.RatesStreamUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideRemoteRepository(remoteRepositoryImpl: RemoteRepositoryImpl): RemoteRepository

    @Binds
    abstract fun provideLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository



}