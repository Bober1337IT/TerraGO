package com.terrago.app.navigation.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.terrago.app.navigation.Screen.AnimalFormRoutes
import com.terrago.app.ui.screens.animalform.ObjectFormScreen
import com.terrago.app.ui.screens.animalform.AnimalFormScreen
import com.terrago.app.ui.screens.animalform.SpeciesFormScreen
import com.terrago.app.viewmodel.animalformviewmodel.AnimalFormViewModel

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.animalFormGraph(
    viewModel: AnimalFormViewModel,
    navController: NavController
) {
    // ADD
    composable(AnimalFormRoutes.ADD) {
    AnimalFormScreen(
        animalId = null,
        viewModel = viewModel,
        navController = navController, // Pass this
        onBack = { navController.popBackStack() }
    )
}
    // EDIT
    composable(
        route = AnimalFormRoutes.EDIT,
        arguments = listOf(navArgument("animalId") { type = NavType.LongType })
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getLong("animalId")
        AnimalFormScreen(
            animalId = id,
            viewModel = viewModel,
            navController = navController,
            onBack = { navController.popBackStack() }
        )
    }


    // NEW OBJECT SCREEN
    composable(AnimalFormRoutes.NEW_HABITAT) {
        ObjectFormScreen(
            viewModel = viewModel,
            onBack = { navController.popBackStack() }
        )
    }

    // NEW SPECIES SCREEN
    composable(AnimalFormRoutes.NEW_SPECIES) {
        SpeciesFormScreen(
            viewModel = viewModel,
            onBack = { navController.popBackStack() }
        )
    }
}