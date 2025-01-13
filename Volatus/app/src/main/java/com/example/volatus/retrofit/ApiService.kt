package com.example.volatus.retrofit
import com.example.volatus.ui.features.airtportList.Airport
import com.example.volatus.ui.features.ticketlist.Ticket
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("airport/getAll")
    suspend fun getAllAirportList() : Response<List<Airport>>

    @GET("ticket/getTickets")
    suspend fun getTickets(
        @Query("departureAirportId") departureAirportId:Int,
        @Query("arrivalAirportId") arrivalAirportId:Int,
        @Query("date") date:String,

    ): Response<List<Ticket>>

}