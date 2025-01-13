package com.example.volatus.di

import com.example.volatus.retrofit.ApiService
import com.example.volatus.retrofit.ServiceConstants.BASE_URL
import com.example.volatus.ui.features.airtportList.AirportService
import com.example.volatus.ui.features.airtportList.AirportServiceInterface
import com.example.volatus.ui.features.ticketlist.departureTicketList.DepartureTicketListService
import com.example.volatus.ui.features.ticketlist.departureTicketList.DepartureTicketListServiceInterface
import com.example.volatus.ui.features.ticketlist.returnTicket.ReturnTicketListScreen
import com.example.volatus.ui.features.ticketlist.returnTicket.ReturnTicketListService
import com.example.volatus.ui.features.ticketlist.returnTicket.ReturnTicketListServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideApiService() : ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }

    @Provides
    @Singleton
    fun provideAirportService(apiService: ApiService) : AirportServiceInterface {
        return  AirportService(apiService)
    }

    @Provides
    @Singleton
    fun provideDepartureTicketService(apiService: ApiService) : DepartureTicketListServiceInterface {
        return  DepartureTicketListService(apiService)
    }

    @Provides
    @Singleton
    fun provideReturnTicketService(apiService: ApiService) : ReturnTicketListServiceInterface
    {
        return ReturnTicketListService(apiService)
    }
}