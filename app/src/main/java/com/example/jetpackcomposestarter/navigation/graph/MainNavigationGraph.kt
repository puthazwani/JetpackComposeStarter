package com.example.jetpackcomposestarter.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.jetpackcomposestarter.module.HomeScreen

fun NavGraphBuilder.mainGraph(navController: NavController) {
    navigation(
        route = AppRoutes.Main.Root,
        startDestination = AppRoutes.Main.Home
    ) {
        composable(AppRoutes.Main.Home) {
            HomeScreen()
        }

        travelGraph(navController)
    }
}
