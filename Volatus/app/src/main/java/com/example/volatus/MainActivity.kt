package com.example.volatus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.volatus.navigation.AppNavigation
import com.example.volatus.ui.features.date.DateViewModel
import com.example.volatus.ui.theme.VolatusTheme
import com.example.volatus.ui.features.home.HomeScreen
import com.example.volatus.ui.features.home.HomeViewModel
import com.example.volatus.ui.features.passenger.PassengerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VolatusTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    AppNavigation(
                        modifier = Modifier.fillMaxSize().padding(innerPadding),
                        navHostController = navController,
                        homeViewModel = HomeViewModel(),
                        dateViewModel = DateViewModel(),
                        passengerViewModel = PassengerViewModel()
                    )

                }
            }
        }
    }
}

