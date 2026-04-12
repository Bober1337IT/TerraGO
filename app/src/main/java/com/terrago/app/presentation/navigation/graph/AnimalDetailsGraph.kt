package com.terrago.app.presentation.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.terrago.app.presentation.navigation.Screen
import com.terrago.app.presentation.navigation.Screen.AnimalFormRoutes
import com.terrago.app.presentation.feature.animaldetails.AnimalDetailsScreen
import com.terrago.app.presentation.feature.animals.AnimalsViewModel

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
