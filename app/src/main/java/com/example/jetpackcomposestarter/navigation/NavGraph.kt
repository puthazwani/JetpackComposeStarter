package com.example.jetpackcomposestarter.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposestarter.shared.AppDrawer
import com.example.jetpackcomposestarter.R
import com.example.jetpackcomposestarter.navigation.NavigationRoutes
import com.example.jetpackcomposestarter.navigation.authenticatedGraph
import com.example.jetpackcomposestarter.navigation.unauthenticatedGraph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * This is the main navigation entry point of the app.
 *
 * NavGraph is responsible for:
 * - Showing the correct screen based on navigation route.
 * - Wrapping the app inside a Scaffold with a TopAppBar and Navigation Drawer (if user is logged in).
 * - Delegating navigation graph structure to AppNavHost.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = currentNavBackStackEntry?.destination?.route ?: AllDestinations.HOME
    val currentRoute = currentNavBackStackEntry?.destination?.route
        ?: NavigationRoutes.Authenticated.Home.route


//    val navigationActions = remember(navController) {
//        AppNavigationActions(navController)
//    }

    val showAppScaffold = currentRoute != NavigationRoutes.Unauthenticated.Login.route &&
            currentRoute != NavigationRoutes.Unauthenticated.Activation.route &&
            currentRoute != NavigationRoutes.Unauthenticated.ForgotPassword.route

    if (showAppScaffold) {
        ModalNavigationDrawer(
            drawerContent = {
                AppDrawer(
                    route = currentRoute,
                    modifier = Modifier,
                    navigateToHome = {
                        navController.safeNavigate(NavigationRoutes.Authenticated.Home.route)
                    },
                    navigateToTravel = {
                        navController.safeNavigate(NavigationRoutes.Authenticated.Travel.TravelAuthorization.MyTAF.route)
                    },
                    closeDrawer = { coroutineScope.launch { drawerState.close() } },
                )
            },
            drawerState = drawerState
        ) {
            Scaffold(
//                topBar = {
//                    TopAppBar(
//                        title = { Text(text = currentRoute) },
//                        modifier = Modifier.fillMaxWidth(),
//                        navigationIcon = {
//                            IconButton(onClick = {
//                                coroutineScope.launch { drawerState.open() }
//                            }) {
//                                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
//                            }
//                        },
//                        colors = TopAppBarDefaults.topAppBarColors(
//                            containerColor = MaterialTheme.colorScheme.primaryContainer
//                        )
//                    )
//                },
                topBar = {
                    if (currentRoute == NavigationRoutes.Authenticated.Home.route) {
                        CenteredLogoAppBarWithNavIcon {
                            coroutineScope.launch { drawerState.open() }
                        }
                    } else {
                        TopAppBar(
                            title = { Text(text = currentRoute) },
                            navigationIcon = {
                                IconButton(onClick = {
                                    coroutineScope.launch { drawerState.open() }
                                }) {
                                    Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    }
                },
                modifier = Modifier
            ) { innerPadding ->
                AppNavHost(navController, Modifier.padding(innerPadding))
            }
        }
    } else {
        AppNavHost(navController, modifier)
    }
}

fun NavHostController.safeNavigate(route: String) {
    this.navigate(route) {
        popUpTo(this@safeNavigate.graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenteredLogoAppBarWithNavIcon(
    onNavClick: () -> Unit
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App Logo",
                    modifier = Modifier.height(40.dp)
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onNavClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        }
    )
}






@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.Unauthenticated.NavigationRoute.route,
        modifier = modifier
    ) {
        unauthenticatedGraph(navController = navController)
        authenticatedGraph(navController = navController)
    }
}


