package com.example.valutor.data.mappers

import com.example.valutor.domain.models.Rate
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JsonMapper @Inject constructor() {

    fun jsonToRateList(json: String): List<Rate> {
        return try {
            json.split("\"rates\":")[1]
                .replace("[{}\"]".toRegex(), "")
                .split(",")
                .map {
                    with(it.split(":")) {
                        Rate(name = this[0], value = this[1].toDouble())
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }



}



