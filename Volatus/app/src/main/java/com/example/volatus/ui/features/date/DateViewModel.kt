package com.example.volatus.ui.features.date


import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.volatus.utils.FormaterType
import com.example.volatus.utils.convertCalendar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale


enum class DateValueType {
    NOw,
    DISABLE,
    DefaultDate,
    Selected,
    Between

}

interface DateViewModelInterface {
    var uiState: StateFlow<DateContract.UiState>

    fun selectedData(mountIndex: Int, dayIndex: Int)
    fun getSelectedDate(mountIndex: Int, day: Int?): LocalDate
}

class DateViewModel : ViewModel(), DateViewModelInterface {

    private val _uiState = MutableStateFlow(DateContract.UiState())
    override var uiState: StateFlow<DateContract.UiState> = _uiState

    private var dates = mutableListOf<Triple<Int, Int, LocalDate>>()

    private var selected: Boolean = false
    private var oldMountIndex: Int = -1
    private var oldDayIndex: Int = -1


    override fun selectedData(mountIndex: Int, dayIndex: Int) {

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

    override fun getSelectedDate(mountIndex: Int, day: Int?): LocalDate {
        val selected = dates.filter { it.first == mountIndex && it.second == day }.first().third
        return selected
    }

    private fun updateDay(mountIndex: Int, dayIndex: Int, dateType: DateValueType) {
        val currentMap = _uiState.value.daysCalender.toMutableMap()
        val targetList = currentMap[mountIndex]?.toMutableList() ?: return
        targetList[dayIndex] = targetList[dayIndex].copy(first = dateType)
        currentMap[mountIndex] = targetList
        _uiState.value = _uiState.value.copy(
            daysCalender = currentMap
        )
    }


    fun createCalender(getDepartureDate: String, getReturnDate: String, control: Boolean) {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))

        val departureCalender = getDepartureDate.convertCalendar(FormaterType.TypeThree)
        val returnCalender = getReturnDate.convertCalendar(FormaterType.TypeThree)

        for (i in 0..2) {
            val monthData = mutableMapOf<String, List<Pair<DateValueType, String?>>>()

            val counter = if (i == 0) 0 else 1
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + counter)

            val firstDayOfMonth = calendar.getActualMinimum(Calendar.DAY_OF_MONTH)
            val lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

            calendar.set(Calendar.DAY_OF_MONTH, firstDayOfMonth)
            val startWeekDay = calendar.get(Calendar.DAY_OF_WEEK) - 2

            val daysInMonth = mutableListOf<Pair<DateValueType, String?>>()
            val daysList = mutableListOf<Pair<DateValueType, Int?>>()
            val datesList = mutableListOf<Date>()

            for (j in 0 until startWeekDay) {
                daysInMonth.add(Pair(DateValueType.DISABLE, null))
                daysList.add(Pair(DateValueType.DISABLE, null))

            }

            for (day in firstDayOfMonth..lastDayOfMonth) {
                val rangeCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                rangeCalendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), day)
                rangeCalendar.timeZone = TimeZone.getTimeZone("UTC")


                val todayLocalDate = LocalDate.now()
                val rangeLocalDate =
                    LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, day)
                val type: DateValueType
                val diff = ChronoUnit.DAYS.between(todayLocalDate, rangeLocalDate)
                val departureLocalDate = LocalDate.of(
                    departureCalender.get(Calendar.YEAR),
                    departureCalender.get(Calendar.MONTH) + 1,
                    departureCalender.get(Calendar.DAY_OF_MONTH)
                )
                var returnLocalDate = LocalDate.of(
                    returnCalender.get(Calendar.YEAR),
                    returnCalender.get(Calendar.MONTH) + 1,
                    returnCalender.get(Calendar.DAY_OF_MONTH)
                )

                if (rangeLocalDate == departureLocalDate || rangeLocalDate == returnLocalDate) {
                    type = DateValueType.Selected
                } else if (todayLocalDate == rangeLocalDate) {
                    type = DateValueType.NOw
                } else if ((control && todayLocalDate > rangeLocalDate) || (!control && departureLocalDate > rangeLocalDate)) {
                    type = DateValueType.DISABLE
                } else if (departureCalender < rangeCalendar && rangeCalendar < returnCalender) {
                    type = DateValueType.Between
                } else {
                    type = DateValueType.DefaultDate
                }

                datesList.add(rangeCalendar.time)
                dates.add(Triple(i, day, rangeLocalDate))

                daysList.add(Pair(type, day))

            }

            _uiState.value = _uiState.value.copy(
                daysCalender = _uiState.value.daysCalender.toMutableMap().apply {
                    put(i, daysList)
                }
            )

            val monthFormat = SimpleDateFormat(FormaterType.TypeTwo.formatString, Locale.getDefault())
            _uiState.value = _uiState.value.copy(
                mountCalender = _uiState.value.mountCalender.toMutableMap().apply {
                    put(i, monthFormat.format(calendar.time))
                }
            )
            monthData[monthFormat.format(calendar.time)] = daysInMonth
        }
    }
}
