package com.example.jetpackcomposestarter.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposestarter.AppDrawer
import com.example.jetpackcomposestarter.R
import com.example.jetpackcomposestarter.navigation.graph.AppRoutes
import com.example.jetpackcomposestarter.navigation.graph.authGraph
import com.example.jetpackcomposestarter.navigation.graph.mainGraph
import com.example.jetpackcomposestarter.shared.components.AppTopBar
import kotlinx.coroutines.CoroutineScope
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
    val currentRoute = currentNavBackStackEntry?.destination?.route
        ?: AppRoutes.Main.Home


//    val navigationActions = remember(navController) {
//        AppNavigationActions(navController)
//    }

    val showAppScaffold = currentRoute != AppRoutes.Auth.Login &&
            currentRoute != AppRoutes.Auth.Activation &&
            currentRoute != AppRoutes.Auth.ForgotPassword


    if (showAppScaffold) {
        ModalNavigationDrawer(
            drawerContent = {
                AppDrawer(
                    route = currentRoute,
                    modifier = Modifier,
                    navigateToHome = {
                        navController.safeNavigate(AppRoutes.Main.Home)
                    },
                    navigateToTravel = {
                        navController.safeNavigate(AppRoutes.Travel.Authorization.MyTaf)
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
                    when (currentRoute) {
                        AppRoutes.Main.Home -> {
                            CenteredLogoAppBarWithNavIcon {
                                coroutineScope.launch { drawerState.open() }
                            }
                        }

                        AppRoutes.Travel.Authorization.MyTaf -> {
                            AppTopBar(
                                title = "My TAF",
                                onNavClick = { coroutineScope.launch { drawerState.open() } },
                                showSearch = true,
                                showAdd = true,
                                onSearchClick = { /* handle search */ },
                                onAddClick = {
                                    navController.navigate(AppRoutes.Travel.Authorization.TafFormNew)
                                }

                            )
                        }

                        AppRoutes.Travel.Authorization.TafFormNew -> {
                            AppTopBar(
                                title = "Travel Authorization Form",
                                navigationIcon = {
                                    IconButton(onClick = { navController.popBackStack() }) {
                                        Icon(
                                            imageVector = Icons.Filled.ArrowBack,
                                            contentDescription = "Back"
                                        )
                                    }
                                },
                                showSearch = false,
                                showAdd = false
                            )
                        }

                        else -> {
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
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            )
                        }
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
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    )
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.Auth.Root,
        modifier = modifier
    ) {
        authGraph(navController)
        mainGraph(navController)
    }
}




