package com.example.volatus.hilt.shared

import androidx.lifecycle.ViewModel
import com.example.volatus.R
import com.example.volatus.ui.features.airtportList.Airport
import com.example.volatus.ui.features.passenger.Passenger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject


class SharedModel  : ViewModel(){
    private val _airportUiState = MutableStateFlow(SharedContract.AirportUiState())
    var airportUiState : StateFlow<SharedContract.AirportUiState> = _airportUiState

    private val _dateState = MutableStateFlow(SharedContract.DateState())
    var dateState : StateFlow<SharedContract.DateState> = _dateState

    private val _passengerState = MutableStateFlow(SharedContract.PassengerState())
    var passengerState : StateFlow<SharedContract.PassengerState> = _passengerState

    init {
        val formatter = DateTimeFormatter.ofPattern("MMMM dd,yyyy")
        _dateState.value = _dateState.value.copy(
            departureDateText = formatter.format(_dateState.value.departureDate),
            returnDateText = formatter.format(_dateState.value.returnDate)
        )


        val passengerList:List<Passenger> = listOf(
            Passenger(title = "Adult",
                ageSpaceTitle = R.string.adult_range_title,
                1,true),
            Passenger(title = "Child",
                ageSpaceTitle = R.string.child_range_title,
                0,true),
            Passenger(title = "Baby",
                ageSpaceTitle = R.string.baby_range_title,
                0,true),
        )

        val list = passengerList.map { if (it.count != 0) "${it.count} ${it.title}" else "" }
        val text = list.filter { it.isNotEmpty() }.joinToString(",")

        _passengerState.value = _passengerState.value.copy(
            passengerList = passengerList,
            passengerText = text
        )

    }


    fun onAction(onAction:SharedContract.SharedAction) {
        when(onAction) {
            is SharedContract.SharedAction.selectedAirport -> updateAirport(onAction.type,onAction.airport)
             is SharedContract.SharedAction.selectedDate -> selectedDate(onAction.type,onAction.date)

            SharedContract.SharedAction.onTappedSwapIcon ->onTappedSwapIcon()
            is SharedContract.SharedAction.updatePassengerList -> updatePassengerListAction(onAction.passengerList)
        }
    }

   private fun updateAirport(type:Boolean?,airport: Airport){
        type?.let {
            if (it) {
                _airportUiState.value = _airportUiState.value.copy(
                    fromAirport = airport,
                    fromAirportTextString = "${airport.code} - ${airport.name}"
                )
            }else{
                _airportUiState.value = _airportUiState.value.copy(
                    toAirport = airport,
                    toAirportText = "${airport.code} - ${airport.name}"
                )
            }
        }
    }

    private fun onTappedSwapIcon() {
        val tempFromAirport = _airportUiState.value.fromAirport
        val tempToAirport = _airportUiState.value.toAirport

        _airportUiState.value = _airportUiState.value.copy(
            fromAirport = tempToAirport,
            fromAirportTextString =  if (tempToAirport == null) "Choosen" else "${tempToAirport.code} - ${tempToAirport.name}",
            toAirport = tempFromAirport,
            toAirportText =  if (tempFromAirport == null) "Choosen" else "${tempFromAirport.code} - ${tempFromAirport.name}",

        )
    }

    private fun selectedDate(type:Boolean?,selectedDate:LocalDate){
         val formatter = DateTimeFormatter.ofPattern("MMMM dd,yyyy")
        val dateText = formatter.format(selectedDate)
        type?.let {
            if (it) {
                _dateState.value = _dateState.value.copy(
                    departureDate = selectedDate,
                    departureDateText = dateText
                )
            }else{
                _dateState.value = _dateState.value.copy(
                    returnDate = selectedDate,
                    returnDateText = dateText

                )
            }
        }
    }

    private fun updatePassengerListAction(passengerList:List<Passenger>) {
        val list = passengerList.map { if (it.count != 0) "${it.count} ${it.title}" else "" }
        val text = list.filter { it.isNotEmpty() }.joinToString(",")

        _passengerState.value = _passengerState.value.copy(
            passengerList = passengerList,
            passengerText = text
        )
    }
}