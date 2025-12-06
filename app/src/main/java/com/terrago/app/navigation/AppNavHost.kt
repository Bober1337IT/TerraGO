package com.terrago.app.navigation

import com.terrago.app.viewmodel.animalsviewmodel.AnimalsViewModelFactory
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.terrago.app.database.repositories.AnimalsRepository
import com.terrago.app.db.TerraGoDatabase
import com.terrago.app.navigation.graph.animalFormGraph
import com.terrago.app.navigation.graph.animalDetailsGraph
import com.terrago.app.navigation.graph.animalsGraph
import com.terrago.app.viewmodel.animalsviewmodel.AnimalsViewModel

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
        animalsGraph(animalsViewModel, navController)
        animalDetailsGraph(animalsViewModel, navController)
        animalFormGraph(animalsViewModel, navController)
    }
}