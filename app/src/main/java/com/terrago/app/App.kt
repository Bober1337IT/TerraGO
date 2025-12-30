package com.terrago.app

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.terrago.app.db.TerraGoDatabase
import com.terrago.app.navigation.AppNavHost
import com.terrago.app.ui.components.BottomNavBar
import com.terrago.app.ui.components.BottomNavItem
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
