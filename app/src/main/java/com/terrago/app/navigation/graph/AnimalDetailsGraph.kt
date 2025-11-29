package com.terrago.app.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.terrago.app.navigation.Screen
import com.terrago.app.ui.screens.AnimalDetailsScreen
import com.terrago.app.viewmodel.AnimalsViewModel

fun NavGraphBuilder.addAnimalDetailsGraph(
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
            animalId = id
        )
    }
}
