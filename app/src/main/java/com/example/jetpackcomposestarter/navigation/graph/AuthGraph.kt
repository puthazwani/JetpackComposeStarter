package com.example.jetpackcomposestarter.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.jetpackcomposestarter.module.EmptyComingSoon
import com.example.jetpackcomposestarter.module.ForgotPasswordScreen
import com.example.jetpackcomposestarter.module.LoginScreen

fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation(
        route = AppRoutes.Auth.Root,
        startDestination = AppRoutes.Auth.Login
    ) {
        // Login
        composable(AppRoutes.Auth.Login) {
            LoginScreen(
                onNavigateToActivation = {
                    navController.navigate(AppRoutes.Auth.Activation)
                },
                onNavigateToForgotPassword = {
                    navController.navigate(AppRoutes.Auth.ForgotPassword)
                },
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(AppRoutes.Main.Root) {
                        popUpTo(AppRoutes.Auth.Root) { inclusive = true }
                    }
                },
            )
        }

        // Forgot Password
        composable(AppRoutes.Auth.ForgotPassword) {
            ForgotPasswordScreen(
                onNavigateBack = { navController.navigateUp() },
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(AppRoutes.Main.Root) {
                        popUpTo(AppRoutes.Auth.Root) { inclusive = true }
                    }
                }
            )
        }

        // Activation Screen
        composable(AppRoutes.Auth.Activation) {
            EmptyComingSoon(onNavigateBack = { navController.navigateUp() })
        }
    }
}
