package com.example.volatus.ui.features.airtportList

import com.example.volatus.retrofit.ApiService
import javax.inject.Inject

interface AirportServiceInterface{
    suspend fun fetchAllAirportList()
    suspend fun getAllAirportList():Pair<List<Airport>,Boolean>
}


class AirportService @Inject constructor(private val apiService: ApiService) : AirportServiceInterface{
    private  var airportList : Pair<List<Airport>,Boolean> = Pair(emptyList(),true)

    override suspend fun fetchAllAirportList(){
        try {
            val response = apiService.getAllAirportList()
            if (response.isSuccessful){
                val list = response.body() ?: emptyList()
                airportList = Pair(list,false)
            }else{
                airportList = Pair(emptyList(),true)
            }
        }catch (t: Throwable){
            airportList = Pair(emptyList(),true)
        }
    }


    override suspend fun getAllAirportList(): Pair<List<Airport>, Boolean> {
        return airportList
    }
}