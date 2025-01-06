package com.example.volatus.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

enum class FormaterType(val formatString: String) {

    TypeFour("E d MMM"),
    TypeFive("dd/MM/yyyy")
}

fun LocalDate.formatter(type: FormaterType): String {
    val dateFormatter = DateTimeFormatter.ofPattern(type.formatString)
    return this.format(dateFormatter)
}

fun LocalDate.subtractYears(years: Int): LocalDate {
    return this.minusYears(years.toLong())
}