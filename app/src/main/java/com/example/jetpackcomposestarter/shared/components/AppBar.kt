package com.example.jetpackcomposestarter.shared.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposestarter.R


//@Composable
//fun AppBar() {
//    TopAppBar (
//        title = {
//            Text(
//                text = stringResource(
//                    id = R.string.travel_advance
//                )
//            )
//        }
//    )
//}

@Composable
fun SimpleTopAppBar() {
    TopAppBar(
        windowInsets = AppBarDefaults.topAppBarWindowInsets,
        title = { Text("Travel Advance") },
        navigationIcon = {
            IconButton(onClick = {  }) { //TODO
                Icon(Icons.Filled.Menu, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = {  }) { //TODO
                Icon(Icons.Filled.Add, contentDescription = "Add New")
            }
            IconButton(onClick = {  }) { //TODO
                Icon(Icons.Filled.Search, contentDescription = "Search Filter")
            }
        },
    )
}

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
)


@Composable
fun RTAFBottomNavigationBar(
    items: List<BottomNavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(
        tonalElevation = 0.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedIndex,
                onClick = { onItemSelected(index) },
                icon = {
                    if (item.badgeCount > 0) {
                        BadgedBox(badge = {
                            Badge { Text(item.badgeCount.toString()) }
                        }) {
                            Icon(item.icon, contentDescription = item.label)
                        }
                    } else {
                        Icon(item.icon, contentDescription = item.label)
                    }
                },
                label = { Text(item.label) }
            )
        }
    }
}


