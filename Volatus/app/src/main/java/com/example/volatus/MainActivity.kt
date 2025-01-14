package com.example.volatus

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.volatus.shared.SharedModel
import com.example.volatus.navigation.AppNavigation
import com.example.volatus.ui.features.airtportList.AirportListViewModel
import com.example.volatus.ui.features.date.DateViewModel
import com.example.volatus.ui.theme.VolatusTheme
import com.example.volatus.ui.features.home.HomeViewModel
import com.example.volatus.ui.features.passenger.PassengerViewModel
import com.example.volatus.ui.features.passengerInfo.PassengerInfoViewModel
import com.example.volatus.ui.features.ticketlist.departureTicketList.DepartureTicketListViewModel
import com.example.volatus.ui.features.ticketlist.returnTicket.ReturnTicketListViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VolatusTheme {
                SetStatusBarColor()
                Scaffold(
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val sharedModel = SharedModel()
                    AppNavigation(
                        modifier = Modifier.fillMaxSize().padding(innerPadding),
                        navHostController = navController,
                        sharedModel,
                        homeViewModel = hiltViewModel<HomeViewModel>(),
                        airportListViewModel =  hiltViewModel<AirportListViewModel>(),
                        dateViewModel = hiltViewModel<DateViewModel>(),
                        passengerViewModel = hiltViewModel<PassengerViewModel>(),
                        departureTicketListViewModel = hiltViewModel<DepartureTicketListViewModel>(),
                        returnTicketListViewModel = hiltViewModel<ReturnTicketListViewModel>(),
                        passengerInfoViewModel = hiltViewModel<PassengerInfoViewModel>()
                    )

                }
            }
        }
    }
}

@Composable
fun SetStatusBarColor() {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(Color.Red)
    }
}
