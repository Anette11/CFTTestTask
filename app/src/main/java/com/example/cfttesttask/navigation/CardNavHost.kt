package com.example.cfttesttask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cfttesttask.ui.components.history.HistoryScreen
import com.example.cfttesttask.ui.components.home.HomeScreen

@Composable
fun CardNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Home.route,
    onPhoneClick: (String) -> Unit,
    onUrlClick: (String) -> Unit,
    onCoordinatesClick: (Double, Double) -> Unit,
    onError: (String) -> Unit
) = NavHost(
    navController = navController,
    startDestination = startDestination
) {
    composable(
        route = Screen.Home.route
    ) {
        HomeScreen(
            onPhoneClick = { phone: String -> onPhoneClick(phone) },
            onUrlClick = { url: String -> onUrlClick(url) },
            onCoordinatesClick = { latitude: Double, longitude: Double ->
                onCoordinatesClick(latitude, longitude)
            },
            onHistoryClick = { navController.navigate(route = Screen.History.route) },
            onError = { message: String -> onError(message) }
        )
    }
    composable(
        route = Screen.History.route
    ) {
        HistoryScreen(
            onBackClick = { navController.navigateUp() },
            onPhoneClick = { phone: String -> onPhoneClick(phone) },
            onUrlClick = { url: String -> onUrlClick(url) },
            onCoordinatesClick = { latitude: Double, longitude: Double ->
                onCoordinatesClick(latitude, longitude)
            },
            onError = { message: String -> onError(message) }
        )
    }
}