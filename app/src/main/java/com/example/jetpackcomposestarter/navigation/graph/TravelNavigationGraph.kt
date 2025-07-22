package com.example.jetpackcomposestarter.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.jetpackcomposestarter.module.travel.tafViews.TAFFormScreen
import com.example.jetpackcomposestarter.module.travel.tafViews.TAFFormScreenHost
import com.example.jetpackcomposestarter.module.travel.tafViews.TAFListingScreen

fun NavGraphBuilder.travelGraph(navController: NavController) {
    navigation(
        route = AppRoutes.Travel.Root,
        startDestination = AppRoutes.Travel.Authorization.Root
    ) {
        travelAuthorizationGraph(navController)
    }
}

private fun NavGraphBuilder.travelAuthorizationGraph(navController: NavController) {
    navigation(
        route = AppRoutes.Travel.Authorization.Root,
        startDestination = AppRoutes.Travel.Authorization.MyTaf
    ) {
        composable(AppRoutes.Travel.Authorization.MyTaf) {
            TAFListingScreen { taf ->
                navController.navigate("authenticated/travel/authorization/form/${taf.id}")
            }
        }

//        composable(AppRoutes.Travel.Authorization.TafFormNew) {
//            TAFFormScreen()
//        }

        composable(AppRoutes.Travel.Authorization.TafFormNew) { backStackEntry ->
            TAFFormScreenHost(navController = navController)
        }


        // Future tabs
        // composable(AppRoutes.Travel.Authorization.PendingTaf) { ... }
        // composable(AppRoutes.Travel.Authorization.AllTaf) { ... }
    }
}

