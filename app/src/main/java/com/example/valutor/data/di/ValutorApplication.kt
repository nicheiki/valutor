package com.example.valutor.data.di

import android.app.Application
import com.example.valutor.domain.repositories.LocalRepository
import com.example.valutor.domain.repositories.RemoteRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class ValutorApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }

}