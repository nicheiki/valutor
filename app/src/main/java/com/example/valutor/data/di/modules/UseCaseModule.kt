package com.example.valutor.data.di.modules

import com.example.valutor.domain.usecases.RatesStreamUseCaseImpl
import com.example.valutor.presentation.viewModels.RatesStreamUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {

    @Binds
    abstract fun provideRatesStreamUseCase(ratesStreamUseCaseImpl: RatesStreamUseCaseImpl): RatesStreamUseCase

}