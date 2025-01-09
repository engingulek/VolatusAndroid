package com.example.volatus.ui.features.airtportList

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.volatus.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface  AirportListViewModelInterface {
    var uiState : StateFlow<AirportContract.UiState>

    fun onAction(action:AirportContract.UiAction)

}

class AirportListViewModel:ViewModel(),AirportListViewModelInterface {
    private val _uiState = MutableStateFlow(AirportContract.UiState())
    override var uiState: StateFlow<AirportContract.UiState> = _uiState

    private var tempList : List<Airport> = emptyList()

    init {

        tempList =  listOf(
            Airport( 1, "Los Angeles International Airport",  "LAX",  "Los Angeles",  "United States"),
            Airport(2,  "London Heathrow Airport", "LHR",  "London", "United Kingdom"),
            Airport( 3,  "Tokyo Haneda Airport",  "HND",  "Tokyo", "Japan"),
            Airport( 4,  "Dubai International Airport",  "DXB",  "Dubai",  "United Arab Emirates"),
            Airport( 5,  "Istanbul Airport", "IST", "Istanbul","Turkey")
        )
        _uiState.value = _uiState.value.copy(
            airportList = tempList
        )
    }


    override fun onAction(action: AirportContract.UiAction) {
        when(action){
            is AirportContract.UiAction.onSearchAction -> onSearchAction(action.text)
        }
    }


    private fun onSearchAction(text:String) {

        if(text.isEmpty()){
            _uiState.value = _uiState.value.copy(
                airportList = tempList.toMutableList(),
                listMessage = Pair(false, R.string.emptyDefault)
            )
        }else{
           val filterList = tempList.filter { it.name.lowercase().contains(text.lowercase()) }
            _uiState.value = _uiState.value.copy(
                airportList = filterList,
                listMessage =
                if (filterList.isEmpty()) Pair(true, R.string.listAirportEmpty)
                else Pair(false, R.string.emptyDefault)
            )

        }

    }

}