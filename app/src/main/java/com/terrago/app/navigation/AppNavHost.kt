package com.terrago.app.navigation

import com.terrago.app.viewmodel.animalsviewmodel.AnimalsViewModelFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.terrago.app.database.repositories.AnimalsRepository
import com.terrago.app.database.repositories.ObjectsRepository
import com.terrago.app.database.repositories.SpeciesRepository
import com.terrago.app.db.TerraGoDatabase
import com.terrago.app.navigation.graph.animalFormGraph
import com.terrago.app.navigation.graph.animalDetailsGraph
import com.terrago.app.navigation.graph.animalsGraph
import com.terrago.app.viewmodel.animalform.AnimalFormViewModelFactory
import com.terrago.app.viewmodel.animalformviewmodel.AnimalFormViewModel
import com.terrago.app.viewmodel.animalsviewmodel.AnimalsViewModel

@Composable
fun AppNavHost(database: TerraGoDatabase,  modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    // Initialize all Repositories
    val animalsRepository = AnimalsRepository(database)
    val objectsRepository = ObjectsRepository(database)
    val speciesRepository = SpeciesRepository(database)

    // AnimalsViewModel (for the list/details)
    val animalsViewModel: AnimalsViewModel = viewModel(
        factory = AnimalsViewModelFactory(animalsRepository)
    )

    // AnimalFormViewModel (specifically for the Add/Edit screen)
    val animalFormViewModel: AnimalFormViewModel = viewModel(
        factory = AnimalFormViewModelFactory(
            animalsRepository,
            objectsRepository,
            speciesRepository
        )
    )

    NavHost(
        navController = navController,
        startDestination = Screen.AnimalsScreen.route
    ) {
        animalsGraph(animalsViewModel, navController)
        animalDetailsGraph(animalsViewModel, navController)
        animalFormGraph(animalFormViewModel, navController)
    }
}