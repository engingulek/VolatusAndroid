package com.example.volatus.di

import com.example.volatus.ui.features.airtportList.AirportListViewModel
import com.example.volatus.ui.features.airtportList.AirportListViewModelInterface
import com.example.volatus.ui.features.date.DateViewModel
import com.example.volatus.ui.features.date.DateViewModelInterface
import com.example.volatus.ui.features.home.HomeViewModel
import com.example.volatus.ui.features.home.HomeViewModelInterface
import com.example.volatus.ui.features.passenger.PassengerViewModel
import com.example.volatus.ui.features.passenger.PassengerViewModelInterface
import com.example.volatus.ui.features.passengerInfo.PassengerInfoViewModel
import com.example.volatus.ui.features.passengerInfo.PassengerInfoViewModelInterface
import com.example.volatus.ui.features.ticketlist.departureTicketList.DepartureTicketListServiceInterface
import com.example.volatus.ui.features.ticketlist.departureTicketList.DepartureTicketListViewModel
import com.example.volatus.ui.features.ticketlist.departureTicketList.DepartureTicketListViewModelInterface
import com.example.volatus.ui.features.ticketlist.returnTicket.ReturnTicketListViewModel
import com.example.volatus.ui.features.ticketlist.returnTicket.ReturnTicketListViewModelInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModuleModule {

    @Provides
    fun provideHomeViewModelInterface(
        viewModel: HomeViewModel
    ): HomeViewModelInterface = viewModel


    @Provides
    fun provideAirportListViewModelInterface(
        viewModel: AirportListViewModel
    ) : AirportListViewModelInterface = viewModel

    @Provides
    fun provideDateViewModelInterface(
        viewModel:DateViewModel
    ) : DateViewModelInterface = viewModel

    @Provides
    fun provideDepartureTicketListViewModelInterface(
        viewModel:DepartureTicketListViewModel
    ) : DepartureTicketListViewModelInterface= viewModel


    @Provides
    fun provideReturnTicketListViewModelInterface(
        viewModel:ReturnTicketListViewModel
    ) : ReturnTicketListViewModelInterface = viewModel

    @Provides
    fun providePassengerViewModelInterface(
        viewModel: PassengerViewModel

    ) : PassengerViewModelInterface = viewModel

    @Provides
    fun providePassengerInfoViewModelInterface(
        viewModel:PassengerInfoViewModel
    ) : PassengerInfoViewModelInterface = viewModel



}