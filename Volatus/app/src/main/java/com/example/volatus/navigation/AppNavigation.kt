package com.example.volatus.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import com.example.volatus.ui.features.home.HomeScreen
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.volatus.shared.SharedContract
import com.example.volatus.shared.SharedModel
import com.example.volatus.ui.features.airtportList.AirportListScreen
import com.example.volatus.ui.features.date.DateScreen
import com.example.volatus.ui.features.date.DateViewModel
import com.example.volatus.ui.features.home.HomeViewModelInterface
import com.example.volatus.ui.features.passenger.PassengerScreen
import com.example.volatus.ui.features.passenger.PassengerViewModelInterface
import com.example.volatus.ui.features.ticketlist.departureTicketList.DepartureTicketListScreen
import com.example.volatus.ui.features.ticketlist.departureTicketList.DepartureTicketListViewModel
import com.example.volatus.ui.features.ticketlist.departureTicketList.DepartureTicketListViewModelInterface

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    sharedModel: SharedModel,
    homeViewModel: HomeViewModelInterface,
    dateViewModel: DateViewModel,
    passengerViewModel:PassengerViewModelInterface,
    departureTicketListViewModel: DepartureTicketListViewModelInterface,
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = "homeScreen"
    ){

        composable("homeScreen"){


            HomeScreen(
                viewModel = homeViewModel,
                sharedModel = sharedModel,
                navigationToAirportList = { type ->
                    navHostController.navigate("airportList/$type")},
                navigationToDateScreen = { dateType ->
                    dateViewModel.createCalender(
                        sharedModel.dateState.value.departureDateText,
                        sharedModel.dateState.value.returnDateText,
                        control = dateType
                        )
                    navHostController.navigate("dateScreen/$dateType")
                },
                navigationToPassenger = {
                    val passengerList = sharedModel.passengerState.value.passengerList
                    passengerViewModel.getPassengerList(passengerList)
                    navHostController.navigate("passengerScreen")
                },

                navigationToDepartureTicketList = {
                    val departureDate = sharedModel.dateState.value.departureDate
                    departureTicketListViewModel.createDatePrice(departureDate)
                    navHostController.navigate(route ="departureTicketList" )
                }

            )
        }
        composable("airportList/{type}",
            arguments = listOf(navArgument("type"){type= NavType.BoolType})
        ){ backStackEntry ->
            val type = backStackEntry.arguments?.getBoolean("type")

            AirportListScreen(

               selectAirport = {sharedModel.onAction(SharedContract.SharedAction.selectedAirport(type=type,it))},
                onBack = {navHostController.popBackStack()}
            )
        }

        composable(
            "dateScreen/{dateType}",
            arguments = listOf(navArgument("dateType"){type = NavType.BoolType})
        ){backStackEntry ->
            val dateType = backStackEntry.arguments?.getBoolean("dateType")

            DateScreen(
                viewModel = dateViewModel,
                sharedModel = sharedModel,
               selectDateAction = {sharedModel.onAction(SharedContract.SharedAction.selectedDate(type = dateType,it))},
                onBack = {navHostController.popBackStack()}

            )
        }

        composable("passengerScreen"){
            PassengerScreen(
                viewModel = passengerViewModel,
                updatePassenger = {sharedModel.onAction(SharedContract.SharedAction.updatePassengerList(it))},
                onBack = {navHostController.popBackStack()}
            )
        }

        composable("departureTicketList") {
            DepartureTicketListScreen(
                viewModel = departureTicketListViewModel,
                sharedModel = sharedModel
                )
        }


    }
}