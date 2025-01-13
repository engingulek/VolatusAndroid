package com.example.volatus.ui.features.ticketlist

import com.example.volatus.ui.features.airtportList.Airport
import com.google.gson.annotations.SerializedName

data class Ticket (
    @SerializedName("id") val id: Int,
    @SerializedName("airlinesIcon") val airlinesIcon: String,
    @SerializedName("airlineName")  val airlineName: String,
    @SerializedName("planeType")  val planeType: String,
    @SerializedName("departureClock")   val departureClock: String,
    @SerializedName("landingClock")   val landingClock: String,
    @SerializedName("price")   val price: Int,
    @SerializedName("date")   val date: String,
    @SerializedName("departureAirport")   val departureAirport: Airport,
    @SerializedName("arrivalAirport")    val arrivalAirport: Airport
)