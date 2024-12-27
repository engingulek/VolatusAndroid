package com.example.volatus.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.example.volatus.ui.features.home.HomeScreen
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = "homeScreen"
    ){

        composable("homeScreen"){
            HomeScreen()
        }


    }
}