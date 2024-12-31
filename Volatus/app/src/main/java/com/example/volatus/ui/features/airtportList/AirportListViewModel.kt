package com.example.volatus.ui.features.airtportList

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface  AirportListViewModelInterface {
    var uiState : StateFlow<AirportContract.UiState>

}

class AirportListViewModel:ViewModel(),AirportListViewModelInterface {
    private val _uiState = MutableStateFlow(AirportContract.UiState())
    override var uiState: StateFlow<AirportContract.UiState> = _uiState

    init {
        _uiState.value = _uiState.value.copy(
            airportList = listOf(
                Airport( 1, "Los Angeles International Airport",  "LAX",  "Los Angeles",  "United States"),
                Airport(2,  "London Heathrow Airport", "LHR",  "London", "United Kingdom"),
                Airport( 3,  "Tokyo Haneda Airport",  "HND",  "Tokyo", "Japan"),
                Airport( 4,  "Dubai International Airport",  "DXB",  "Dubai",  "United Arab Emirates"),
                Airport( 5,  "Istanbul Airport", "IST", "Istanbul","Turkey")
            )
        )
    }

}