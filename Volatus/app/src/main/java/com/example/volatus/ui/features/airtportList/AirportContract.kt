package com.example.volatus.ui.features.airtportList

import com.example.volatus.R

object AirportContract {

    data class UiState(

        var searchPlaceholder:Int = R.string.searchPlaceholder,
        var airportListTitle:Int = R.string.airportListTitle,
        var airportList : List<Airport> = emptyList(),
        // Boolean -> Status String -> Message
        var listMessage:Pair<Boolean,Int> = Pair(false,R.string.emptyDefault)
    )

    sealed interface UiAction {
        data class onSearchAction(var text:String) :UiAction
    }
}