package com.example.volatus.utils.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter

enum class FormaterType(val formatString: String) {

    TypeTwo("MMMM/yyyy"),
    TypeThree("MMMM dd,yyyy"),
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


