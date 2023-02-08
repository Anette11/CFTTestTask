package com.example.cfttesttask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cfttesttask.ui.components.home.HomeScreen

@Composable
fun CardNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Home.route
) = NavHost(
    navController = navController,
    startDestination = startDestination
) {
    composable(
        route = Screen.Home.route
    ) {
        HomeScreen()
    }
    composable(
        route = Screen.History.route
    ) {

    }
}