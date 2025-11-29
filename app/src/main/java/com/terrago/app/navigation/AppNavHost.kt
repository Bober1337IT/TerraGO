package com.terrago.app.navigation

import AnimalsViewModelFactory
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.terrago.app.database.repositories.AnimalsRepository
import com.terrago.app.db.TerraGoDatabase
import com.terrago.app.navigation.graph.addAnimalDetailsGraph
import com.terrago.app.navigation.graph.addAnimalsGraph
import com.terrago.app.viewmodel.AnimalsViewModel

@Composable
fun AppNavHost(database: TerraGoDatabase) {
    val navController = rememberNavController()

    val animalsRepository = AnimalsRepository(database)

    val animalsViewModel: AnimalsViewModel = viewModel(
        factory = AnimalsViewModelFactory(animalsRepository)
    )

    NavHost(
        navController = navController,
        startDestination = Screen.AnimalsScreen.route
    ) {
        addAnimalsGraph(animalsViewModel, navController)
        addAnimalDetailsGraph(animalsViewModel, navController)
    }
}