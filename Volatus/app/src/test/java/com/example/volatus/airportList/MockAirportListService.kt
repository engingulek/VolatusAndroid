package com.example.volatus.airportList

import com.example.volatus.ui.features.airtportList.Airport
import com.example.volatus.ui.features.airtportList.AirportServiceInterface

class MockAirportListService : AirportServiceInterface {
    var mockAirportListError : Boolean = false

    private  var airportList : Pair<List<Airport>,Boolean> = Pair(emptyList(),true)
    override suspend fun fetchAllAirportList() {
        if (mockAirportListError){
            airportList = Pair(emptyList(),true)
        }else{
            val list = listOf(
                Airport(1,"TestCountry","City","Code","Airname"),
                Airport(2,"TestCountry2","City2","Code2","Airname2"),
                Airport(2,"TestCountry3","City3","Code3","Airname3"),
            )

            airportList = Pair(list,false)
        }
    }

    override suspend fun getAllAirportList(): Pair<List<Airport>, Boolean> {
        return airportList
    }
}