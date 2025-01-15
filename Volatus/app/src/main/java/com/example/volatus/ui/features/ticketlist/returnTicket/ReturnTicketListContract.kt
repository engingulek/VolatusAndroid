package com.example.volatus.ui.features.ticketlist.returnTicket

import com.example.volatus.R
import com.example.volatus.ui.features.ticketlist.Ticket
import com.example.volatus.ui.features.ticketlist.departureTicketList.DayAndPrice

object ReturnTicketListContract {
    data class State (
        val dayAndPriceList : List<DayAndPrice> = emptyList(),
        val ticketList:List<Ticket> = emptyList(),
        val listMessage : Pair<Boolean,Int> = Pair(true, R.string.errorMessage)
    )

    sealed interface UiAction{
        data class onTappedDate(var index:Int) : UiAction
    }
}