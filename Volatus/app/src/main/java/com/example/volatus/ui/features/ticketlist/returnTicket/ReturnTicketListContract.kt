package com.example.volatus.ui.features.ticketlist.returnTicket

import com.example.volatus.ui.features.ticketlist.departureTicketList.DayAndPrice

object ReturnTicketListContract {
    data class State (
        val dayAndPriceList : List<DayAndPrice> = emptyList()
    )

    sealed interface UiAction{
        data class onTappedDate(var index:Int) : UiAction
    }
}