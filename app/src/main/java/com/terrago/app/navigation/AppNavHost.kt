package com.terrago.app.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import com.terrago.app.presentation.viewmodel.animalsviewmodel.AnimalsViewModelFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.terrago.app.data.repositories.AnimalsRepository
import com.terrago.app.data.repositories.ObjectsRepository
import com.terrago.app.data.repositories.SpeciesRepository
import com.terrago.app.db.TerraGoDatabase
import com.terrago.app.domain.usecase.DeleteAnimalUseCase
import com.terrago.app.domain.usecase.DeleteObjectUseCase
import com.terrago.app.domain.usecase.UpdateAnimalFieldUseCase
import com.terrago.app.domain.usecase.UpsertAnimalUseCase
import com.terrago.app.domain.usecase.UpsertObjectUseCase
import com.terrago.app.domain.usecase.UpsertSpeciesUserCase
import com.terrago.app.navigation.graph.animalFormGraph
import com.terrago.app.navigation.graph.animalDetailsGraph
import com.terrago.app.navigation.graph.animalsGraph
import com.terrago.app.presentation.viewmodel.animalformviewmodel.AnimalFormViewModelFactory
import com.terrago.app.presentation.viewmodel.animalformviewmodel.AnimalFormViewModel
import com.terrago.app.presentation.viewmodel.animalsviewmodel.AnimalsViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(database: TerraGoDatabase,  modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    // Initialize all Repositories
    val animalsRepository = AnimalsRepository(database)
    val objectsRepository = ObjectsRepository(database)
    val speciesRepository = SpeciesRepository(database)

    // AnimalsViewModel (for the list/details)
    val animalsViewModel: AnimalsViewModel = viewModel(
        factory = AnimalsViewModelFactory(
            animalsRepository,
            UpdateAnimalFieldUseCase(animalsRepository)
        )
    )

    // AnimalFormViewModel (specifically for the Add/Edit screen)
    val animalFormViewModel: AnimalFormViewModel = viewModel(
        factory = AnimalFormViewModelFactory(
            animalsRepository,
            objectsRepository,
            speciesRepository,
            UpsertAnimalUseCase(animalsRepository),
            DeleteAnimalUseCase(animalsRepository),
            UpsertObjectUseCase(objectsRepository),
            DeleteObjectUseCase(objectsRepository),
            UpsertSpeciesUserCase(speciesRepository)
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