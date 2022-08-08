package com.example.valutor.data.di

import android.content.Context
import com.example.valutor.data.di.modules.RepositoryModule
import com.example.valutor.data.di.modules.TickersDaoModule
import com.example.valutor.data.di.modules.UseCaseModule
import com.example.valutor.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [RepositoryModule::class, TickersDaoModule::class, UseCaseModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)

}