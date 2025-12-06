package com.terrago.app.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.terrago.app.navigation.graph.routes.AnimalFormRoutes
import com.terrago.app.viewmodel.animalsviewmodel.AnimalsViewModel
import com.terrago.app.ui.screens.animalform.AnimalFormScreen

fun NavGraphBuilder.animalFormGraph(
    viewModel: AnimalsViewModel,
    navController: NavController
) {
    // ADD
    composable(AnimalFormRoutes.ADD) {
        AnimalFormScreen(
            animalId = null,
            viewModel = viewModel,
            onBack = { navController.popBackStack() }
        )
    }

    // EDIT
    composable(
        route = AnimalFormRoutes.EDIT,
        arguments = listOf(navArgument("animalId") { type = NavType.LongType })
    ) { backStackEntry ->
        val id = backStackEntry.arguments!!.getLong("animalId")

        AnimalFormScreen(
            animalId = id,
            viewModel = viewModel,
            onBack = { navController.popBackStack() }
        )
    }
}