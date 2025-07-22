//package com.example.jetpackcomposestarter.navigation.graph
//
//import androidx.navigation.NavController
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.compose.composable
//import androidx.navigation.navigation
//import com.example.jetpackcomposestarter.module.*
//import com.example.jetpackcomposestarter.module.travel.tafViews.TAFListingScreen
//import com.example.jetpackcomposestarter.navigation.NavigationRoutes
//
///**
// * This function defines the navigation graph specifically for the authentication flow.
// * This function adds a nested navigation graph for all authentication-related screens.
// * @param navController - central coordinator managing navigating between destinations
// */
//
//fun NavGraphBuilder.unauthenticatedGraph(navController: NavController) {
//
//    navigation(
//        route = NavigationRoutes.Unauthenticated.NavigationRoute.route,
//        startDestination = NavigationRoutes.Unauthenticated.Login.route
//    ) {
//
//        // Login
//        composable(NavigationRoutes.Unauthenticated.Login.route) {
//            LoginScreen(
//                onNavigateToActivation = {
//                    navController.navigate(NavigationRoutes.Unauthenticated.Activation.route)
//                },
//                onNavigateToForgotPassword = {
//                    navController.navigate(NavigationRoutes.Unauthenticated.ForgotPassword.route)
//                },
//                onNavigateToAuthenticatedRoute = {
//                    navController.navigate(NavigationRoutes.Authenticated.NavigationRoute.route) {
//                        popUpTo(NavigationRoutes.Unauthenticated.NavigationRoute.route) {
//                            inclusive = true
//                        }
//                    }
//                },
//            )
//        }
//
//        // Forgot Password
//        composable(NavigationRoutes.Unauthenticated.ForgotPassword.route) {
//            ForgotPasswordScreen(
//                onNavigateBack = { navController.navigateUp() },
//                onNavigateToAuthenticatedRoute = {
//                    navController.navigate(NavigationRoutes.Authenticated.NavigationRoute.route) {
//                        popUpTo(NavigationRoutes.Unauthenticated.NavigationRoute.route) { inclusive = true }
//                    }
//                }
//            )
//        }
//
//        // Mobile Activation
//        composable(NavigationRoutes.Unauthenticated.Activation.route) {
//            EmptyComingSoon (
//                onNavigateBack = { navController.navigateUp() }
//            )
//        }
//    }
//}
//
//fun NavGraphBuilder.authenticatedGraph(navController: NavController) {
//    navigation(
//        route = NavigationRoutes.Authenticated.NavigationRoute.route,
//        startDestination = NavigationRoutes.Authenticated.Home.route
//    ) {
//        // Home
//        composable(route = NavigationRoutes.Authenticated.Home.route) {
//            HomeScreen()
//        }
//
//        // Travel Main Graph
//        navigation(
//            route = NavigationRoutes.Authenticated.Travel.NavigationRoute.route,
//            startDestination = NavigationRoutes.Authenticated.Travel.TravelAuthorization.NavigationRoute.route
//        ) {
//
//            // Travel Authorization Subgraph
//            navigation(
//                route = NavigationRoutes.Authenticated.Travel.TravelAuthorization.NavigationRoute.route,
//                startDestination = NavigationRoutes.Authenticated.Travel.TravelAuthorization.MyTAF.route
//            ) {
//
//                composable(route = NavigationRoutes.Authenticated.Travel.TravelAuthorization.MyTAF.route) {
//                    TAFListingScreen(
//                        navigateToTAFFormDetailsScreen = {
//                            navController.navigate("taf_form/${it.id}")
//                        }
//                    )
//                }
//
////                composable(
////                    route = NavigationRoutes.Authenticated.Travel.TravelAuthorization.Detail.route + "/{id}",
////                    arguments = listOf(navArgument("id") { type = NavType.LongType })
////                ) { backStackEntry ->
////                    val id = backStackEntry.arguments?.getLong("id")
////                    if (id != null) {
////                        TravelFormDetailScreen(formId = id)
////                    }
////                }
//
//
////                composable(route = NavigationRoutes.Authenticated.Travel.TravelAuthorization.PendingTAF.route) {
////                    EmptyComingSoon({  })
////                }
////
////                composable(route = NavigationRoutes.Authenticated.Travel.TravelAuthorization.AllTAF.route) {
////                    EmptyComingSoon({  })
////                }
//            }
//        }
//    }
//}
