package com.terrago.app

import androidx.compose.runtime.Composable
import com.terrago.app.db.TerraGoDatabase
import com.terrago.app.navigation.AppNavHost
import com.terrago.app.ui.theme.TerraGOTheme

@Composable
fun App(database: TerraGoDatabase) {
    TerraGOTheme {
        AppNavHost(database)
    }
}