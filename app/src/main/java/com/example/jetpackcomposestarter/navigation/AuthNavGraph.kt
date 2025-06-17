package com.example.jetpackcomposestarter.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.jetpackcomposestarter.module.*
import com.example.jetpackcomposestarter.module.travel.*
import com.example.jetpackcomposestarter.navigation.NavigationRoutes

/**
 * This function defines the navigation graph specifically for the authentication flow.
 * This function adds a nested navigation graph for all authentication-related screens.
 * @param navController - central coordinator managing navigating between destinations
 */

fun NavGraphBuilder.unauthenticatedGraph(navController: NavController) {

    navigation(
        route = NavigationRoutes.Unauthenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Unauthenticated.Login.route
    ) {

        // Login
        composable(route = NavigationRoutes.Unauthenticated.Login.route) {
            LoginScreen(
                onNavigateToActivation = {
                    navController.navigate(route = NavigationRoutes.Unauthenticated.Activation.route)
                },
                onNavigateToForgotPassword = {
                    navController.navigate(route = NavigationRoutes.Unauthenticated.ForgotPassword.route)
                },
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                        popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                },
            )
        }

        // Forgot Password
        composable(route = NavigationRoutes.Unauthenticated.ForgotPassword.route) {
            ForgotPasswordScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                        popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Mobile Activation
        composable(route = NavigationRoutes.Unauthenticated.Activation.route) {
            EmptyComingSoon (
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}

fun NavGraphBuilder.authenticatedGraph(navController: NavController) {
    navigation(
        route = NavigationRoutes.Authenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Authenticated.Home.route
    ) {
        // Home
        composable(route = NavigationRoutes.Authenticated.Home.route) {
            HomeScreen()
        }

        // Travel Main Graph
        navigation(
            route = NavigationRoutes.Authenticated.Travel.NavigationRoute.route,
            startDestination = NavigationRoutes.Authenticated.Travel.TravelAuthorization.NavigationRoute.route
        ) {

            // Travel Authorization Subgraph
            navigation(
                route = NavigationRoutes.Authenticated.Travel.TravelAuthorization.NavigationRoute.route,
                startDestination = NavigationRoutes.Authenticated.Travel.TravelAuthorization.MyTAF.route
            ) {

                composable(route = NavigationRoutes.Authenticated.Travel.TravelAuthorization.MyTAF.route) {
                    RTAFListingScreen()
                }

//                composable(route = NavigationRoutes.Authenticated.Travel.TravelAuthorization.PendingTAF.route) {
//                    EmptyComingSoon({  })
//                }
//
//                composable(route = NavigationRoutes.Authenticated.Travel.TravelAuthorization.AllTAF.route) {
//                    EmptyComingSoon({  })
//                }
            }
        }
    }
}
