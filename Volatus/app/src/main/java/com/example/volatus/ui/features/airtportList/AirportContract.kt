package com.example.volatus.ui.features.airtportList

import com.example.volatus.R

object AirportContract {

    data class UiState(
        var searchPlaceholder:Int = R.string.searchPlaceholder,
        var airportListTitle:Int = R.string.airportListTitle,
        var airportList : List<Airport> = emptyList()
    )
}