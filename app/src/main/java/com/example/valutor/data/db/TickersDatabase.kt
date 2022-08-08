package com.example.valutor.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.valutor.data.db.models.Ticker
import com.example.valutor.data.db.models.TickersDao

@Database(entities = [Ticker::class], version = 1, exportSchema = false)
abstract class TickersDatabase : RoomDatabase() {

    abstract val tickersDao: TickersDao

    companion object {
        @Volatile
        private var INSTANCE: TickersDatabase? = null

        fun getInstance(context: Context): TickersDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        TickersDatabase::class.java,
                        "tickers_database"
                    )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}

