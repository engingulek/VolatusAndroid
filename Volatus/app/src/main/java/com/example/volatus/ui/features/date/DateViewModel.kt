package com.example.volatus.ui.features.date


import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.util.Log
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class DateViewModel : ViewModel() {
    val result = mutableListOf<Map<String, List<String?>>>()
    var weeks = listOf("Pzt", "Sal", "Çar", "Per", "Cum", "Cts", "Paz")
    init {
        getCalendarData()
    }


    fun getCalendarData() {
        val calendar = Calendar.getInstance()


        val today = calendar.time
        val dateFormat = SimpleDateFormat("d", Locale.getDefault())
        val monthFormat = SimpleDateFormat("MMMM/yyyy", Locale.getDefault())

        calendar.time = today

        for (i in 0..2) {
            val monthData = mutableMapOf<String, List<String?>>()
            val counter = if (i == 0) 0 else 1
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + counter)

            val firstDayOfMonth = calendar.getActualMinimum(Calendar.DAY_OF_MONTH)
            val lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)


            // Haftanın gününü bul
            calendar.set(Calendar.DAY_OF_MONTH, firstDayOfMonth)
            val startWeekDay = calendar.get(Calendar.DAY_OF_WEEK) - 2

            val daysInMonth = mutableListOf<String?>()

            // İlk gün için boş hücreler ekle
            for (j in 0 until startWeekDay) {
                daysInMonth.add(null)
            }

            // Günü listeye ekle
            for (day in firstDayOfMonth..lastDayOfMonth) {
                daysInMonth.add(day.toString())
            }
            Log.e("MonthFormat","${monthFormat.format(calendar.time)}")
            monthData[monthFormat.format(calendar.time)] = daysInMonth
            result.add(monthData)
        }
        Log.e("result","$result")

    }





}
