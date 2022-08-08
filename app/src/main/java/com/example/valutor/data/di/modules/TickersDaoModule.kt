package com.example.valutor.data.di.modules

import android.content.Context
import com.example.valutor.data.db.TickersDatabase
import com.example.valutor.data.db.models.TickersDao
import dagger.Module
import dagger.Provides

@Module
class TickersDaoModule {

    @Provides
    fun providesTickersDao(context: Context): TickersDao = TickersDatabase.getInstance(context).tickersDao

}

