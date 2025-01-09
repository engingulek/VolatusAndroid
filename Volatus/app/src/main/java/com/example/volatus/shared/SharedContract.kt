package com.example.volatus.shared

import com.example.volatus.ui.features.airtportList.Airport
import com.example.volatus.ui.features.passenger.Passenger
import java.time.LocalDate

object SharedContract {

    data class AirportUiState(
        var fromAirport: Airport? = null,
        var fromAirportTextString: String = "Choose",
        var toAirport: Airport? = null,
        var toAirportText: String = "Choose",
        var airportState: Boolean = false
    )

    data class DateState(
        val departureDate: LocalDate = LocalDate.now(),
        val returnDate: LocalDate = LocalDate.now(),
        val departureDateText: String = "",
        val returnDateText: String = "",
        var returnState: Boolean = false

    )

    data class PassengerState(

        var passengerText: String = "",
        var passengerList: List<Passenger> = emptyList()
    )


    data class TicketState(
        var selectedDepartureTicketId: Int? = null,
        var selectedReturnTicketId: Int? = null
    )


    sealed interface SharedAction {
        data class selectedAirport(var type: Boolean?, var airport: Airport) : SharedAction
        data object onTappedSwapIcon : SharedAction
        data class selectedDate(var type: Boolean?, var date: LocalDate) : SharedAction
        data class updatePassengerList(var passengerList: List<Passenger>) : SharedAction
        data class updateReturnState(var state: Boolean) : SharedAction
        data class selectedDepartureTicket(var id: Int) : SharedAction
        data class selectedReturnTicket(var id: Int) : SharedAction
    }
}