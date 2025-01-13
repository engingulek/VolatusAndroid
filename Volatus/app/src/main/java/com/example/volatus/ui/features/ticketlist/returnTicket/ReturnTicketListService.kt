package com.example.volatus.ui.features.ticketlist.returnTicket

import com.example.volatus.retrofit.ApiService
import com.example.volatus.ui.features.ticketlist.Ticket
import javax.inject.Inject


interface ReturnTicketListServiceInterface {
    suspend fun fetchTicketList(departureId:Int,arrivalId:Int,date:String)
    suspend fun getTicketList():Pair<List<Ticket>,Boolean>
}

class ReturnTicketListService @Inject constructor(
    private val apiService: ApiService
) : ReturnTicketListServiceInterface {

    private var ticketList : Pair<List<Ticket>,Boolean> = Pair(emptyList(),true)
    override suspend fun fetchTicketList(departureId: Int, arrivalId: Int, date: String) {
        try {
            val reponse = apiService.getTickets(departureId,arrivalId,date)
            if (reponse.isSuccessful){
                val list = reponse.body() ?: emptyList()
                ticketList = Pair(list,false)
            }else{

                ticketList = Pair(emptyList(),true)
            }
        }catch (t: Throwable){

            ticketList =  Pair(emptyList(),true)
        }
    }

    override suspend fun getTicketList(): Pair<List<Ticket>, Boolean> {
        return  ticketList
    }
}