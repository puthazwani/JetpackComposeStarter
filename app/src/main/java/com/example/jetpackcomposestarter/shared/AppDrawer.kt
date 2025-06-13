package com.example.jetpackcomposestarter.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
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
import com.example.jetpackcomposestarter.navigation.NavigationRoutes

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
    ModalDrawerSheet(modifier = Modifier) {
        DrawerHeader(modifier)
        Spacer(modifier = Modifier.padding(16.dp))
        NavigationDrawerItem(
            modifier = Modifier.padding(horizontal = 8.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.home),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            selected = route == NavigationRoutes.Authenticated.Home.route,
            onClick = {
                navigateToHome()
                closeDrawer()
            },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
        )
        Spacer(modifier = Modifier.padding(16.dp))

//        NavigationDrawerItem(
//            label = { Text(text = stringResource(id = R.string.notification_center), fontSize = 20.sp, fontWeight = FontWeight.Medium) },
//            selected = route == AllDestinations.NOTIFICATION_CENTER,
//            onClick = {
//                navigateToNotificationCenter()
//                closeDrawer()
//            },
//            icon = { Icon(imageVector = Icons.Default.Notifications, contentDescription = null) },
//            shape = MaterialTheme.shapes.large
//        )
//
//        NavigationDrawerItem(
//            label = { Text(text = stringResource(id = R.string.leave), fontSize = 20.sp, fontWeight = FontWeight.Medium) },
//            selected = route == AllDestinations.LEAVE,
//            onClick = {
//                navigateToLeave()
//                closeDrawer()
//            },
//            icon = { Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = null) },
//            shape = MaterialTheme.shapes.medium
//        )
//
//        NavigationDrawerItem(
//            label = { Text(text = stringResource(id = R.string.claims), fontSize = 20.sp, fontWeight = FontWeight.Medium) },
//            selected = route == AllDestinations.CLAIM,
//            onClick = {
//                navigateToClaim()
//                closeDrawer()
//            },
//            icon = { Icon(imageVector = Icons.Default.Money, contentDescription = null) },
//            shape = MaterialTheme.shapes.small
//        )
//
//        NavigationDrawerItem(
//            label = { Text(text = stringResource(id = R.string.payroll), fontSize = 20.sp, fontWeight = FontWeight.Medium) },
//            selected = route == AllDestinations.PAYROLL,
//            onClick = {
//                navigateToPayroll()
//                closeDrawer()
//            },
//            icon = { Icon(imageVector = Icons.Default.MonetizationOn, contentDescription = null) },
//            shape = MaterialTheme.shapes.small
//        )
//
//        NavigationDrawerItem(
//            label = { Text(text = stringResource(id = R.string.time), fontSize = 20.sp, fontWeight = FontWeight.Medium) },
//            selected = route == AllDestinations.TIME,
//            onClick = {
//                navigateToTime()
//                closeDrawer()
//            },
//            icon = { Icon(imageVector = Icons.Default.AccessTime, contentDescription = null) },
//            shape = MaterialTheme.shapes.small
//        )

        NavigationDrawerItem(
            modifier = Modifier.padding(horizontal = 8.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.travel),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            selected = route == NavigationRoutes.Authenticated.Travel.TravelAuthorization.MyTAF.route,
            onClick = {
                navigateToTravel()
                closeDrawer()
            },
            icon = { Icon(imageVector = Icons.Default.Flight, contentDescription = null) },
        )

//        NavigationDrawerItem(
//            label = { Text(text = stringResource(id = R.string.qr_scan), fontSize = 20.sp, fontWeight = FontWeight.Medium) },
//            selected = route == AllDestinations.QR_SCAN,
//            onClick = {
//                navigateToQRScan()
//                closeDrawer()
//            },
//            icon = { Icon(imageVector = Icons.Default.QrCodeScanner, contentDescription = null) },
//            shape = MaterialTheme.shapes.medium
//        )
    }
}

@Composable
fun DrawerHeader(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondary)
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        Image(
            painterResource(id = R.drawable.profile_picture),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(16.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.padding(16.dp))

        Text(
            text = stringResource(id = R.string.app_name),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

