package com.example.valutor.domain.models

enum class SortOrder(val text: String) {
    BY_NAME_ASC("По алфавиту"),
    BY_NAME_DESC("По алфавиту (уб.)"),
    BY_VALUE_ASC("По значению"),
    BY_VALUE_DESC("По значению (уб.)")
}
