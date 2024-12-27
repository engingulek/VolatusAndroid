package com.example.volatus.ui.features.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.volatus.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface HomeViewModelInterface{
    var uiState : StateFlow<HomeContract.UiState>
    var dateState : StateFlow<HomeContract.DateState>
    var tripState : StateFlow<HomeContract.TripTypeState>
    var locationState : StateFlow<HomeContract.LocationState>
    var passengerState : StateFlow<HomeContract.PassengerState>


    fun onAction(uiAction: HomeContract.UiAction)




}


class HomeViewModel : ViewModel(), HomeViewModelInterface{

    private val _uiState = MutableStateFlow(HomeContract.UiState())
    override  var uiState : StateFlow<HomeContract.UiState> = _uiState

    private val _dateState  =  MutableStateFlow(HomeContract.DateState())
    override var dateState : StateFlow<HomeContract.DateState> = _dateState

    private val _tripState = MutableStateFlow(HomeContract.TripTypeState())
    override var tripState: StateFlow<HomeContract.TripTypeState> = _tripState

    private val _locationState = MutableStateFlow(HomeContract.LocationState())
    override var locationState: StateFlow<HomeContract.LocationState> = _locationState

    private val _passengerState = MutableStateFlow(HomeContract.PassengerState())
    override var passengerState: StateFlow<HomeContract.PassengerState> = _passengerState


    init {
        _passengerState.value = _passengerState.value.copy(
            passengers = "1 Adult"
        )
    }



    override fun onAction(uiAction: HomeContract.UiAction) {
        when(uiAction){

            HomeContract.UiAction.OnClickOneWay ->  clickedOneWay()
            HomeContract.UiAction.OnClickRoundedTrip ->clickedRoundedWay()
        }
    }

    private fun clickedOneWay(){
        _dateState.value = _dateState.value.copy(
            returnVisible = true
        )

        _tripState.value = _tripState.value.copy(

            oneWayTripeType = HomeContract.TripeTypeData(
                containerColor = R.color.red,
                contentColor = R.color.white
            ),
            roundedTripeType = HomeContract.TripeTypeData(
                containerColor = R.color.white,
                contentColor = R.color.red
            )
        )
    }

    private fun clickedRoundedWay(){

        _dateState.value = _dateState.value.copy(
            returnVisible = false
        )

        _tripState.value = _tripState.value.copy(

            oneWayTripeType = HomeContract.TripeTypeData(
                containerColor = R.color.white,
                contentColor = R.color.red
            ),
            roundedTripeType = HomeContract.TripeTypeData(
                containerColor = R.color.red,
                contentColor = R.color.white
            )
        )
    }




}