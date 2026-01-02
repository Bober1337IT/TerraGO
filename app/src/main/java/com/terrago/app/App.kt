package com.terrago.app

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.terrago.app.db.TerraGoDatabase
import com.terrago.app.navigation.AppNavHost
import com.terrago.app.ui.components.bottombar.BottomNavBar
import com.terrago.app.ui.components.bottombar.BottomNavItem
import com.terrago.app.ui.theme.TerraGOTheme


@Composable
fun App(database: TerraGoDatabase) {
    TerraGOTheme {
        var selectedBottomItem by remember {
            mutableStateOf(BottomNavItem.LEFT)
        }
        Scaffold(
            contentWindowInsets = WindowInsets(0),
            bottomBar = {
                BottomNavBar(
                    selected = selectedBottomItem,
                    onItemSelected = { item ->
                        selectedBottomItem = item
                        // nawigacja
                    }
                )
            }
        ) {
            //        padding ->
            AppNavHost(database)
             }
        }
    }
