package com.example.valutor.domain.utils

import com.example.valutor.domain.models.Rate
import com.example.valutor.domain.models.SortOrder

object SortUtil {

    fun sort(rates: List<Rate>, sortOrder: SortOrder): List<Rate> = when (sortOrder) {
        SortOrder.BY_NAME_ASC -> {
            rates.sortedBy { it.name }
        }
        SortOrder.BY_NAME_DESC -> {
            rates.sortedByDescending { it.name }
        }
        SortOrder.BY_VALUE_ASC -> {
            rates.sortedBy { it.value }
        }
        SortOrder.BY_VALUE_DESC -> {
            rates.sortedByDescending { it.value }
        }
    }

}