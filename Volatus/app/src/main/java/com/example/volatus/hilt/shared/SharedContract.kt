package com.example.volatus.hilt.shared

import com.example.volatus.R
import com.example.volatus.ui.features.airtportList.Airport
import com.example.volatus.ui.features.passenger.Passenger
import java.time.LocalDate

object SharedContract {
    data class AirportUiState(
        var fromAirport:Airport? = null,
        var fromAirportTextString: String = "Choose",
        var toAirport: Airport? = null,
        var toAirportText:String = "Choose"
    )

    data class DateState(
        val departureDate:LocalDate = LocalDate.now(),
        val returnDate:LocalDate = LocalDate.now(),
        val departureDateText:String = "",
        val returnDateText:String = "",
        var returnState:Boolean = false

    )

    data class PassengerState(

        var passengerText:String = "",
        var passengerList:List<Passenger> = emptyList()
    )


    sealed interface SharedAction{
        data class selectedAirport(var type:Boolean?,var airport: Airport) : SharedAction
        data object onTappedSwapIcon: SharedAction
         data class selectedDate(var type:Boolean?,var date: LocalDate) : SharedAction
       data class updatePassengerList(var passengerList: List<Passenger>):SharedAction
    }
}