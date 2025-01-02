package com.example.volatus.ui.features.date


import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.util.Log
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit
import kotlin.time.Duration

enum class DateValueType{
    NOw,
    DISABLE,
    DefaultDate,
    Selected,

}

class DateViewModel : ViewModel() {

    val mountCalender = MutableStateFlow(mutableMapOf<Int, String>())
    var daysCalender = MutableStateFlow(mutableMapOf<Int,List<Pair<DateValueType,String?>>>())


    var weeks = listOf("Pzt", "Sal", "Çar", "Per", "Cum", "Cts", "Paz")
    init {
        getCalendarData()
    }


    fun selectedData(index:Int) {
        Log.e("index","${index}")
    }


    fun getCalendarData() {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))



        val dayFormat = SimpleDateFormat("d", Locale.getDefault())
        val monthFormat = SimpleDateFormat("MMMM/yyyy", Locale.getDefault())
        val controlDate = SimpleDateFormat("d/MMMM/yyyy", Locale.getDefault())



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
            val daysList = mutableListOf<Pair<DateValueType, String?>>()
            // İlk gün için boş hücreler ekle
            for (j in 0 until startWeekDay) {
                daysInMonth.add(Pair(DateValueType.DISABLE,null))
               daysList.add(Pair(DateValueType.DISABLE,null))

            }


            // Günü listeye ekle
            for (day in firstDayOfMonth..lastDayOfMonth) {
                val rangeCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                rangeCalendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),day)
                rangeCalendar.timeZone = TimeZone.getTimeZone("UTC")



                val todayLocalDate = LocalDate.now()
                val rangeLocalDate = LocalDate.of(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH) + 1,day)
                val type:DateValueType
                val diff = ChronoUnit.DAYS.between(todayLocalDate,rangeLocalDate)

                if (diff < 0){
                    type  =  DateValueType.DISABLE
                }else if (diff.toInt() == 0 ){
                    type  =  DateValueType.NOw
                }else{
                    type  =  DateValueType.DefaultDate
                }







               daysList.add(Pair(type,"${day}"))


            }
             daysCalender.value = daysCalender.value.toMutableMap().apply {
                 put(i,daysList)
             }

            mountCalender.value = mountCalender.value.toMutableMap().apply {
                put(i,monthFormat.format(calendar.time))
            }
            monthData[monthFormat.format(calendar.time)] = daysInMonth


        }


    }





}
