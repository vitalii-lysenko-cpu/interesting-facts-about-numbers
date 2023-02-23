package com.example.interesting_facts_about_numbers.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.interesting_facts_about_numbers.ui.presentation.NumberFactScreen
import com.example.interesting_facts_about_numbers.ui.presentation.StartScreen

sealed class NavRoute(val route: String) {
    object StartScreen : NavRoute(route = "start_screen")
    object NumberFactScreen : NavRoute(route = "number_fact_screen/{number}")
}

@Composable
fun FactNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.StartScreen.route) {
        composable(NavRoute.StartScreen.route)
        {
            StartScreen(navController = navController)
        }
        composable(
            NavRoute.NumberFactScreen.route,
            arguments = listOf(navArgument("number") {
                type = NavType.StringType
                defaultValue = "random"
                nullable = true
            })
        ) {
            NumberFactScreen(navController = navController)
        }
    }
}