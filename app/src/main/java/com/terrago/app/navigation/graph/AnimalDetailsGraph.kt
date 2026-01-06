package com.terrago.app.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.terrago.app.navigation.Screen
import com.terrago.app.navigation.graph.routes.AnimalFormRoutes
import com.terrago.app.ui.screens.animaldetails.AnimalDetailsScreen
import com.terrago.app.viewmodel.animalsviewmodel.AnimalsViewModel

fun NavGraphBuilder.animalDetailsGraph(
    viewModel: AnimalsViewModel,
    navController: NavController
) {
    composable(
        route = Screen.AnimalDetailsScreen.route,
        arguments = listOf(navArgument("animalId") { nullable = false })
    ) { entry ->

        val id = entry.arguments!!.getString("animalId")!!.toLong()

        AnimalDetailsScreen(
            viewModel = viewModel,
            animalId = id,
            onBack = { navController.popBackStack() },
            onEditClick = { navController.navigate(AnimalFormRoutes.edit(id)) }
        )
    }
}
