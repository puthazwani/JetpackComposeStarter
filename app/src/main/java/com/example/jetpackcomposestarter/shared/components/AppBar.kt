package com.example.jetpackcomposestarter.shared.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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