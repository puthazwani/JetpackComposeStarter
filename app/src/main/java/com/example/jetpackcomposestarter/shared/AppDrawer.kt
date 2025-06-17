package com.example.jetpackcomposestarter.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposestarter.R
import com.example.jetpackcomposestarter.module.HomeScreen
import com.example.jetpackcomposestarter.navigation.NavigationRoutes
import com.example.jetpackcomposestarter.shared.theme.JetpackComposeStarterTheme

enum class DrawerMenuState {
    Main, LeaveSubmenu, ClaimSubmenu, PayrollSubmenu, TimeSubmenu, TravelSubmenu
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer(
    route: String,
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit = {},
    navigateToNotificationCenter: () -> Unit = {},
    navigateToLeave: () -> Unit = {},
    navigateToClaim: () -> Unit = {},
    navigateToPayroll: () -> Unit = {},
    navigateToTime: () -> Unit = {},
    navigateToTravel: () -> Unit = {},
    navigateToQRScan: () -> Unit = {},
    closeDrawer: () -> Unit = {}
) {
    val drawerMenuState = remember { mutableStateOf(DrawerMenuState.Main) }
    ModalDrawerSheet(
        modifier = Modifier,
        drawerContainerColor = MaterialTheme.colorScheme.surfaceContainer
    ) {
        DrawerHeader(modifier = Modifier, route = NavigationRoutes.Unauthenticated.Login.route)
        HorizontalDivider()
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer_padding)))

        when (drawerMenuState.value) {
            DrawerMenuState.Main -> {
                NavigationDrawerItem(
                    label = { Text(stringResource(R.string.home)) },
                    selected = route == NavigationRoutes.Authenticated.Home.route,
                    onClick = {
                        navigateToHome()
                        closeDrawer()
                    },
                    icon = { Icon(Icons.Default.Home, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                NavigationDrawerItem(
                    label = { Text(stringResource(R.string.notification_center)) },
                    selected = false,
                    onClick = {
                        navigateToNotificationCenter()
                        closeDrawer()
                    },
                    icon = { Icon(Icons.Default.Inbox, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                NavigationDrawerItem(
                    label = { Text(stringResource(id = R.string.leave)) },
                    selected = false,
                    onClick = { drawerMenuState.value = DrawerMenuState.LeaveSubmenu },
                    icon = { Icon(Icons.Default.CalendarToday, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                NavigationDrawerItem(
                    label = { Text(stringResource(id = R.string.claims)) },
                    selected = false,
                    onClick = { drawerMenuState.value = DrawerMenuState.ClaimSubmenu },
                    icon = { Icon(Icons.Default.ReceiptLong, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                NavigationDrawerItem(
                    label = { Text(stringResource(id = R.string.payroll)) },
                    selected = false,
                    onClick = { drawerMenuState.value = DrawerMenuState.PayrollSubmenu },
                    icon = { Icon(imageVector = Icons.Default.CreditCard, contentDescription = null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                NavigationDrawerItem(
                    label = { Text(stringResource(id = R.string.time)) },
                    selected = false,
                    onClick = { drawerMenuState.value = DrawerMenuState.TimeSubmenu},
                    icon = { Icon(imageVector = Icons.Default.AccessTimeFilled, contentDescription = null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                NavigationDrawerItem(
                    label = { Text(stringResource(R.string.travel)) },
                    selected = route.contains("travel"),
                    onClick = { drawerMenuState.value = DrawerMenuState.TravelSubmenu },
                    icon = { Icon(Icons.Default.Flight, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                NavigationDrawerItem(
                    label = { Text(stringResource(R.string.qr_scan)) },
                    selected = false,
                    onClick = {
                        navigateToQRScan()
                        closeDrawer()
                    },
                    icon = { Icon(Icons.Default.QrCodeScanner, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

            DrawerMenuState.LeaveSubmenu -> {
                NavigationDrawerItem(
                    label = { Text("Back") },
                    selected = false,
                    onClick = { drawerMenuState.value = DrawerMenuState.Main },
                    icon = { Icon(Icons.Default.ArrowBack, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Leave Transactions") },
                    selected = false,
                    onClick = {
                        navigateToLeave()
                        closeDrawer()
                    },
                    icon = { Icon(Icons.Default.Description, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Leave Balance") },
                    selected = false,
                    onClick = {
//                         navigateToAdvance()
                        closeDrawer()
                    },
                    icon = { Icon(Icons.Default.List, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

            DrawerMenuState.ClaimSubmenu -> {
                NavigationDrawerItem(
                    label = { Text("Back") },
                    selected = false,
                    onClick = { drawerMenuState.value = DrawerMenuState.Main },
                    icon = { Icon(Icons.Default.ArrowBack, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Claims Transactions") },
                    selected = false,
                    onClick = {
                        navigateToClaim()
                        closeDrawer()
                    },
                    icon = { Icon(Icons.Default.Description, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Entitlement") },
                    selected = false,
                    onClick = {
//                         navigateToAdvance()
                        closeDrawer()
                    },
                    icon = { Icon(Icons.Default.List, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

            DrawerMenuState.PayrollSubmenu -> {
                NavigationDrawerItem(
                    label = { Text("Back") },
                    selected = false,
                    onClick = { drawerMenuState.value = DrawerMenuState.Main },
                    icon = { Icon(Icons.Default.ArrowBack, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Payslip") },
                    selected = false,
                    onClick = {
                        navigateToClaim()
                        closeDrawer()
                    },
                    icon = { Icon(Icons.Default.Description, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                NavigationDrawerItem(
                    label = { Text("EA Form") },
                    selected = false,
                    onClick = {
//                         navigateToAdvance()
                        closeDrawer()
                    },
                    icon = { Icon(Icons.Default.Description, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

            DrawerMenuState.TimeSubmenu -> {
                NavigationDrawerItem(
                    label = { Text("Back") },
                    selected = false,
                    onClick = { drawerMenuState.value = DrawerMenuState.Main },
                    icon = { Icon(Icons.Default.ArrowBack, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Work Schedule") },
                    selected = false,
                    onClick = {
                        navigateToTime()
                        closeDrawer()
                    },
                    icon = { Icon(Icons.Default.CalendarToday, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Check In/Out") },
                    selected = false,
                    onClick = {
//                         navigateToAdvance()
                        closeDrawer()
                    },
                    icon = { Icon(Icons.Default.PersonPinCircle, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Overtime Application") },
                    selected = false,
                    onClick = {
//                         navigateToAdvance()
                        closeDrawer()
                    },
                    icon = { Icon(Icons.Default.Description, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Overtime Bulk Application") },
                    selected = false,
                    onClick = {
//                         navigateToAdvance()
                        closeDrawer()
                    },
                    icon = { Icon(Icons.Default.Description, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

            DrawerMenuState.TravelSubmenu -> {
                NavigationDrawerItem(
                    label = { Text("Back") },
                    selected = false,
                    onClick = { drawerMenuState.value = DrawerMenuState.Main },
                    icon = { Icon(Icons.Default.ArrowBack, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Travel Authorisations") },
                    selected = route == NavigationRoutes.Authenticated.Travel.TravelAuthorization.MyTAF.route,
                    onClick = {
                        navigateToTravel() // or a specific navigateToTAF()
                        closeDrawer()
                    },
                    icon = { Icon(Icons.Default.Description, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Travel Advances") },
                    selected = false,
                    onClick = {
//                         navigateToAdvance()
                        closeDrawer()
                    },
                    icon = { Icon(Icons.Default.AttachMoney, null) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
fun DrawerHeader(
    route: String,
    modifier: Modifier = Modifier,
    navigateToLogout: () -> Unit = {},
) {
    val isLogout = route == NavigationRoutes.Authenticated.Travel.TravelAuthorization.MyTAF.route

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.polygonbackground),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize(),
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.header_padding))
                .fillMaxWidth()
        ) {
            Image(
                painterResource(id = R.drawable.user),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.header_image_size))
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer_padding)))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Ishak Abdul Aziz",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSecondary
                    )

                    Text(
                        text = "Vice President, Retail Sales Dept",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
                IconButton(
                    onClick = { navigateToLogout() },
                ) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = "Logout",
                    )
                }
            }
        }
    }
}


@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun AppDrawerLightScreenPreviewDark() {
    JetpackComposeStarterTheme(darkTheme = false) {
        AppDrawer(modifier = Modifier, route = NavigationRoutes.Authenticated.Home.route)
    }
}

@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun AppDrawerDarkScreenPreviewDark() {
    JetpackComposeStarterTheme(darkTheme = true) {
        AppDrawer(modifier = Modifier, route = NavigationRoutes.Authenticated.Home.route)
    }
}