package com.example.volatus.ui.features.date

object DateContract {

    data class UiState(
        val weeks:List<String> =  listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
        val mountCalender: MutableMap<Int, String> = mutableMapOf(),
        var daysCalender: MutableMap<Int,List<Pair<DateValueType,Int?>>> = mutableMapOf()
    )
}