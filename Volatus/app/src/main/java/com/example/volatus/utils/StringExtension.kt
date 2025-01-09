package com.example.volatus.utils

import android.icu.util.Calendar
import java.text.SimpleDateFormat

fun String.isValidTCNumber(): Boolean {

    if (this.length != 11) return false


    if (this[0] == '0') return false


    if (!this.all { it.isDigit() }) return false

    var sumOdd = 0
    var sumEven = 0


    for (i in 0 until 9) {
        val digit = this[i].digitToInt()
        if (i % 2 == 0) {
            sumOdd += digit
        } else {
            sumEven += digit
        }
    }


    val tenthDigit = (sumOdd * 7 - sumEven) % 10
    if (this[9].digitToInt() != tenthDigit) return false


    val totalSum = this.substring(0, 10).sumOf { it.digitToInt() }
    val eleventhDigit = totalSum % 10
    if (this[10].digitToInt() != eleventhDigit) return false

    return true
}


fun String.convertCalendar(type: FormaterType) :Calendar {
    val formatter = SimpleDateFormat(type.formatString)
    val date = formatter.parse(this)
    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar

}
