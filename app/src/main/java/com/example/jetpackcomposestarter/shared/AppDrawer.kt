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
import androidx.compose.ui.graphics.Color
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
import com.example.jetpackcomposestarter.shared.theme.JetpackComposeStarterTheme

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
        HorizontalDivider()
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer_padding)))
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

        NavigationDrawerItem(
            modifier = Modifier.padding(horizontal = 8.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.notification_center),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            selected = false,
            onClick = {
                navigateToNotificationCenter()
                closeDrawer()
            },
            icon = { Icon(imageVector = Icons.Default.Notifications, contentDescription = null) },
        )

        NavigationDrawerItem(
            modifier = Modifier.padding(horizontal = 8.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.leave),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            selected = false,
            onClick = {
                navigateToLeave()
                closeDrawer()
            },
            icon = { Icon(imageVector = Icons.Default.CalendarToday, contentDescription = null) },
        )

        NavigationDrawerItem(
            modifier = Modifier.padding(horizontal = 8.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.claims),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            selected = false,
            onClick = {
                navigateToClaim()
                closeDrawer()
            },
            icon = { Icon(imageVector = Icons.Default.ReceiptLong, contentDescription = null) },
        )

        NavigationDrawerItem(
            modifier = Modifier.padding(horizontal = 8.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.payroll),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            selected = false,
            onClick = {
                navigateToClaim()
                closeDrawer()
            },
            icon = { Icon(imageVector = Icons.Default.CreditCard, contentDescription = null) },
        )

        NavigationDrawerItem(
            modifier = Modifier.padding(horizontal = 8.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.time),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            selected = false,
            onClick = {
                navigateToTime()
                closeDrawer()
            },
            icon = { Icon(imageVector = Icons.Default.AccessTime, contentDescription = null) },
        )

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

        NavigationDrawerItem(
            modifier = Modifier.padding(horizontal = 8.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.qr_scan),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            selected = false,
            onClick = {
                navigateToQRScan()
                closeDrawer()
            },
            icon = { Icon(imageVector = Icons.Default.QrCodeScanner, contentDescription = null) },
        )
    }
}

@Composable
fun DrawerHeader(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
//            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(dimensionResource(id = R.dimen.header_padding))
            .fillMaxWidth()
    ) {

        Image(
            painterResource(id = R.drawable.profile_picture),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(dimensionResource(id = R.dimen.header_image_size))
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer_padding)))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Ishak Abdul Aziz",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
//                color = MaterialTheme.colorScheme.onPrimary,
            )
//            Spacer(modifier = Modifier.width(24.dp))
//            Icon(
//                imageVector = Icons.Default.Logout,
//                contentDescription = "Logout",
//            )
        }

        Text(
            text = "Vice President, Retail Sales Dept",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )
    }
}

//@Composable
//fun DrawerHeader(modifier: Modifier) {
//    Column(
//        modifier = modifier
////            .background(MaterialTheme.colorScheme.secondary)
//            .padding(dimensionResource(id = R.dimen.header_padding))
//            .fillMaxWidth()
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            // Profile Image
//            Image(
//                painter = painterResource(id = R.drawable.profile_picture),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .size(dimensionResource(id = R.dimen.header_image_size))
//                    .clip(CircleShape)
//            )
//
//            Spacer(modifier = Modifier.width(12.dp))
//
//            // Name and Position+Division stacked
//            Column {
//                Text(
//                    text = "Nur Azimi Binti Ahmad",
//                    style = MaterialTheme.typography.bodyLarge,
////                    color = MaterialTheme.colorScheme.onPrimary,
//                )
//                Text(
//                    text = "Vice President, Retail Sales Dept",
//                    style = MaterialTheme.typography.bodyMedium,
//                    color = Color.Gray
//                )
//            }
//        }
//    }
//}


@Preview(showBackground = true)
@Composable
fun DrawerHeaderPreview() {
    JetpackComposeStarterTheme {
        AppDrawer(modifier = Modifier, route = NavigationRoutes.Authenticated.Home.route)
    }
}