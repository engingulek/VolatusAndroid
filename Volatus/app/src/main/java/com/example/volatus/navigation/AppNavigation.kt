package com.example.volatus.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import com.example.volatus.ui.features.home.HomeScreen
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.volatus.ui.features.airtportList.Airport
import com.example.volatus.ui.features.airtportList.AirportListScreen
import com.example.volatus.ui.features.date.DateScreen
import com.example.volatus.ui.features.home.HomeContract
import com.example.volatus.ui.features.home.HomeViewModel
import com.example.volatus.ui.features.home.HomeViewModelInterface

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    homeViewModel: HomeViewModelInterface,
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = "homeScreen"
    ){

        composable("homeScreen"){
            HomeScreen(
                viewModel = homeViewModel,
                navigationToAirportList = { type ->
                    navHostController.navigate("airportList/$type")},
                navigationToDateScreen = { dateType ->

                    navHostController.navigate("dateScreen/$dateType")
                }

            )
        }
        composable("airportList/{type}",
            arguments = listOf(navArgument("type"){type= NavType.BoolType})
        ){ backStackEntry ->
            val type = backStackEntry.arguments?.getBoolean("type")
            AirportListScreen(
                selectAirport = {homeViewModel.onAction(HomeContract.UiAction.selectedAirport(type= type,it))},
                onBack = {navHostController.popBackStack()}
            )
        }

        composable(
            "dateScreen/{dateType}",
            arguments = listOf(navArgument("dateType"){type = NavType.BoolType})
        ){backStackEntry ->
            val dateType = backStackEntry.arguments?.getBoolean("dateType")
            DateScreen(
               selectDateAction = {homeViewModel.onAction(HomeContract.UiAction.selectedDate(type = dateType,it))},
                onBack = {navHostController.popBackStack()}

            )
        }


    }
}