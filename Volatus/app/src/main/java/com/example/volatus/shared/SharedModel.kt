package com.example.volatus.shared

import androidx.lifecycle.ViewModel
import com.example.volatus.R
import com.example.volatus.ui.features.airtportList.Airport
import com.example.volatus.ui.features.passenger.Passenger
import com.example.volatus.ui.features.ticketlist.Ticket
import com.example.volatus.utils.extensions.FormaterType
import com.example.volatus.utils.extensions.formatter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate


class SharedModel  : ViewModel(){
    private val _airportUiState = MutableStateFlow(SharedContract.AirportUiState())
    var airportUiState : StateFlow<SharedContract.AirportUiState> = _airportUiState

    private val _dateState = MutableStateFlow(SharedContract.DateState())
    var dateState : StateFlow<SharedContract.DateState> = _dateState

    private val _passengerState = MutableStateFlow(SharedContract.PassengerState())
    var passengerState : StateFlow<SharedContract.PassengerState> = _passengerState

    private  val _ticketState = MutableStateFlow(SharedContract.TicketState())
    var ticketState : StateFlow<SharedContract.TicketState> = _ticketState

    private var fromAirport:Airport? = null
    private var toAirport:Airport? = null

    init {
        _dateState.value = _dateState.value.copy(
            departureDateText = _dateState.value.departureDate.formatter(FormaterType.TypeThree),
            returnDateText = _dateState.value.returnDate.formatter(FormaterType.TypeThree)
        )
        
        val passengerList: List<Passenger> = listOf(
            Passenger(
                title = "Adult",
                ageSpaceTitle = R.string.adult_range_title,
                1, true
            ),
            Passenger(
                title = "Child",
                ageSpaceTitle = R.string.child_range_title,
                0, true
            ),
            Passenger(
                title = "Baby",
                ageSpaceTitle = R.string.baby_range_title,
                0, true
            ),
        )

        val list = passengerList.map { if (it.count != 0) "${it.count} ${it.title}" else "" }
        val text = list.filter { it.isNotEmpty() }.joinToString(",")

        _passengerState.value = _passengerState.value.copy(
            passengerList = passengerList,
            passengerText = text
        )

    }

    fun onAction(onAction: SharedContract.SharedAction) {
        when (onAction) {
            is SharedContract.SharedAction.selectedAirport -> updateAirport(
                onAction.type,
                onAction.airport
            )

            is SharedContract.SharedAction.selectedDate -> selectedDate(
                onAction.type,
                onAction.date
            )

            SharedContract.SharedAction.onTappedSwapIcon -> onTappedSwapIcon()
            is SharedContract.SharedAction.updatePassengerList -> updatePassengerListAction(onAction.passengerList)
            is SharedContract.SharedAction.updateReturnState -> updateReturnState(onAction.state)
            is SharedContract.SharedAction.selectedDepartureTicket -> selectedDepartureTicketId(
                onAction.ticket
            )

            is SharedContract.SharedAction.selectedReturnTicket -> selectedReturnTicketId(onAction.ticket)
        }
    }


    private fun updateAirport(type: Boolean?, airport: Airport) {
        type?.let {
            if (it) {
                _airportUiState.value = _airportUiState.value.copy(
                    fromAirport = airport,
                    fromAirportTextString = "${airport.code} - ${airport.airname}"
                )
                fromAirport = airport
            } else {
                _airportUiState.value = _airportUiState.value.copy(
                    toAirport = airport,
                    toAirportText = "${airport.code} - ${airport.airname}"
                )
                toAirport = airport
            }
            _airportUiState.value = _airportUiState.value.copy(
                airportState = (toAirport != null && fromAirport != null)
                        && (fromAirport?.id != toAirport?.id)
            )
        }
    }

    private fun onTappedSwapIcon() {
        val tempFromAirport = _airportUiState.value.fromAirport
        val tempToAirport = _airportUiState.value.toAirport

        _airportUiState.value = _airportUiState.value.copy(
            fromAirport = tempToAirport,
            fromAirportTextString = if (tempToAirport == null) "Choosen" else "${tempToAirport.code} - ${tempToAirport.airname}",
            toAirport = tempFromAirport,
            toAirportText = if (tempFromAirport == null) "Choosen" else "${tempFromAirport.code} - ${tempFromAirport.airname}",
            )
    }

    private fun selectedDate(type: Boolean?, selectedDate: LocalDate) {

        val dateText = selectedDate.formatter(FormaterType.TypeThree)
        type?.let {
            if (it) {
                _dateState.value = _dateState.value.copy(
                    departureDate = selectedDate,
                    departureDateText = dateText
                )
                val returnDate = _dateState.value.returnDate
                if (selectedDate > returnDate) {
                    _dateState.value = _dateState.value.copy(
                        returnDate = selectedDate,
                        returnDateText = dateText
                    )
                }
            } else {
                _dateState.value = _dateState.value.copy(
                    returnDate = selectedDate,
                    returnDateText = dateText,

                    )
            }
        }
    }

    private fun updatePassengerListAction(passengerList: List<Passenger>) {
        val list = passengerList.map { if (it.count != 0) "${it.count} ${it.title}" else "" }
        val text = list.filter { it.isNotEmpty() }.joinToString(",")

        _passengerState.value = _passengerState.value.copy(
            passengerList = passengerList,
            passengerText = text
        )
    }

    private fun updateReturnState(state: Boolean) {
        _dateState.value = _dateState.value.copy(
            returnState = state
        )
    }

    private fun selectedDepartureTicketId(ticket: Ticket) {
        _ticketState.value = _ticketState.value.copy(
            selectedDepartureTicket = ticket
        )
    }

    private fun selectedReturnTicketId(ticket: Ticket) {
        _ticketState.value = _ticketState.value.copy(
            selectedReturnTicket  = ticket
        )
    }
}