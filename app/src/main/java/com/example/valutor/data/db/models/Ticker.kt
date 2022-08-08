package com.example.valutor.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tickers")
data class Ticker(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean
)


