package com.example.volatus.retrofit
import com.example.volatus.ui.features.airtportList.Airport
import retrofit2.Response
import retrofit2.http.GET
interface ApiService {
    @GET("airport/getAll")
    suspend fun getAllAirportList() : Response<List<Airport>>
}