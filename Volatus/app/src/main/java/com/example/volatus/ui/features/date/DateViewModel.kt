package com.example.volatus.ui.features.date


import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.util.Log
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

enum class DateValueType{
    NOw,
    DISABLE,
    DefaultDate

}

class DateViewModel : ViewModel() {
    val result = mutableListOf<Map<String, List<Pair<DateValueType,String?>>>>()
    var weeks = listOf("Pzt", "Sal", "Çar", "Per", "Cum", "Cts", "Paz")
    init {
        getCalendarData()
    }


    fun getCalendarData() {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))


        val today = calendar.time
        val dayFormat = SimpleDateFormat("d", Locale.getDefault())
        val monthFormat = SimpleDateFormat("MMMM/yyyy", Locale.getDefault())
        val controlDate = SimpleDateFormat("d/MMMM/yyyy", Locale.getDefault())

        calendar.time = today

        for (i in 0..2) {
            val monthData = mutableMapOf<String, List<Pair<DateValueType,String?>>>()
            val counter = if (i == 0) 0 else 1
           calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + counter)

            val firstDayOfMonth = calendar.getActualMinimum(Calendar.DAY_OF_MONTH)
            val lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)


            // Haftanın gününü bul
            calendar.set(Calendar.DAY_OF_MONTH, firstDayOfMonth)
            val startWeekDay = calendar.get(Calendar.DAY_OF_WEEK) - 2

            val daysInMonth = mutableListOf<Pair<DateValueType,String?>>()

            // İlk gün için boş hücreler ekle
            for (j in 0 until startWeekDay) {
                daysInMonth.add(Pair(DateValueType.DISABLE,null))
            }

            // Günü listeye ekle
            for (day in firstDayOfMonth..lastDayOfMonth) {
                val rangeCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                rangeCalendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),day)
                rangeCalendar.timeZone = TimeZone.getTimeZone("UTC")

                val diff = rangeCalendar.time.time - today.time
                val diffInDays = TimeUnit.MILLISECONDS.toDays(diff)
                Log.e("diff","$diffInDays")

                if (diffInDays.toInt() == 0){
                    daysInMonth.add(Pair(DateValueType.NOw,day.toString()))
                    Log.e("engin","al")

                }else if (diffInDays < 0){
                    daysInMonth.add(Pair(DateValueType.DISABLE,day.toString()))
                }else{
                    daysInMonth.add(Pair(DateValueType.DefaultDate,day.toString()))

                }

            }

            monthData[monthFormat.format(calendar.time)] = daysInMonth
            result.add(monthData)
        }


    }





}