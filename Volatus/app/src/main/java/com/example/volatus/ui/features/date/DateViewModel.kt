package com.example.volatus.ui.features.date


import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.util.Log
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.ZoneId
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
    Between

}

class DateViewModel : ViewModel() {

    val mountCalender = MutableStateFlow(mutableMapOf<Int, String>())
    var daysCalender = MutableStateFlow(mutableMapOf<Int,List<Pair<DateValueType,Int?>>>())
     private var dates = mutableListOf<Triple<Int, Int, LocalDate>>()
    val navTitle = MutableStateFlow<String>("")




    private var selected:Boolean = false
    private  var oldMountIndex : Int = -1
    private  var oldDayIndex:Int = -1


    var weeks = listOf("Pzt", "Sal", "Çar", "Per", "Cum", "Cts", "Paz")
    init {
       // val calendar = Calendar.getInstance()
       // getCalendarData(calendar)
    }



    fun selectedData(mountIndex:Int,dayIndex:Int) {

        if (selected) {
            selected = false
            updateDay(oldMountIndex, oldDayIndex, DateValueType.DefaultDate)
            updateDay(mountIndex, dayIndex, DateValueType.Selected)
            selected = true
        } else {
            updateDay(mountIndex, dayIndex, DateValueType.Selected)
            selected = true
        }

        oldMountIndex = mountIndex
        oldDayIndex = dayIndex
    }

    fun getSelectedDate(mountIndex: Int,day:Int?) : LocalDate  {
        val selected = dates.filter { it.first == mountIndex && it.second == day }.first().third
        return selected
    }

    private fun updateDay(mountIndex: Int, dayIndex: Int, dateType: DateValueType) {
        val currentMap = daysCalender.value.toMutableMap()
        val targetList = currentMap[mountIndex]?.toMutableList() ?: return
        targetList[dayIndex] = targetList[dayIndex].copy(first = dateType)
        currentMap[mountIndex] = targetList
        daysCalender.value = currentMap
    }


    fun createCalender(getDepartureDate:String,getReturnDate:String,control:Boolean) {
        navTitle.value = if (control) "Departure Date" else "Return Date"


        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
      //  Log.e("getDepartureDate dateviewmodel","${getDepartureDate}")
        val format = "MMMM dd,yyyy"
        val departureCalender = convertToCalendar(getDepartureDate,format)
        val returnCalender = convertToCalendar(getReturnDate,format)
       // Log.e("getDepartureDate dateviewmodel","${departureCalender}")


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
            val daysList = mutableListOf<Pair<DateValueType, Int?>>()
            val datesList = mutableListOf<Date>()
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
                val departureLocalDate = LocalDate.of(
                    departureCalender.get(Calendar.YEAR),
                    departureCalender.get(Calendar.MONTH)+1,
                    departureCalender.get(Calendar.DAY_OF_MONTH))
                var returnLocalDate = LocalDate.of(
                    returnCalender.get(Calendar.YEAR),
                    returnCalender.get(Calendar.MONTH)+1,
                    returnCalender.get(Calendar.DAY_OF_MONTH))

                if(rangeLocalDate == departureLocalDate || rangeLocalDate == returnLocalDate){
                    type  =  DateValueType.Selected
                }else if (todayLocalDate == rangeLocalDate ){
                    type  =  DateValueType.NOw
                }else if( (control && todayLocalDate > rangeLocalDate) || (!control && departureLocalDate > rangeLocalDate)  ){
                    type  =  DateValueType.DISABLE
                }else if(departureCalender<rangeCalendar && rangeCalendar < returnCalender){
                    type = DateValueType.Between
                }else{
                    type  =  DateValueType.DefaultDate
                }



               datesList.add(rangeCalendar.time)
               dates.add(Triple(i,day,rangeLocalDate))


               daysList.add(Pair(type,day))



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

    //TODO : this is will made extension
    fun convertToCalendar(dateStr: String, format: String): Calendar {
        val formatter = SimpleDateFormat(format)
        val date = formatter.parse(dateStr)
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar
    }





}
