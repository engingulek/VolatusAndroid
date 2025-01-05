package com.example.volatus.ui.features.ticketlist.departureTicketList

object DepartureTicketContract {
    data class State (
        val dayAndPriceList : List<DayAndPrice> = emptyList()
    )

    sealed interface UiAction{
        data class onTappedDate(var index:Int) : UiAction
    }

}