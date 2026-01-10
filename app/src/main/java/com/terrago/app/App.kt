package com.terrago.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.terrago.app.database.AppInitializer
import com.terrago.app.database.repositories.SpeciesRepository
import com.terrago.app.db.TerraGoDatabase
import com.terrago.app.navigation.AppNavHost
import com.terrago.app.ui.theme.TerraGOTheme


@Composable
fun App(database: TerraGoDatabase) {
    val context = LocalContext.current
    
    // Call the initializer once when the app starts
    LaunchedEffect(Unit) {
        val speciesRepository = SpeciesRepository(database)
        AppInitializer(context, speciesRepository).initialize()
    }

    TerraGOTheme {
        AppNavHost(database)
    }
}
