package com.terrago.app.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.terrago.app.navigation.Screen
import com.terrago.app.navigation.Screen.AnimalFormRoutes
import com.terrago.app.ui.screens.animals.AnimalsScreen
import com.terrago.app.viewmodel.animalsviewmodel.AnimalsViewModel

fun NavGraphBuilder.animalsGraph(
    viewModel: AnimalsViewModel,
    navController: NavController
) {
    composable(Screen.AnimalsScreen.route) {
        AnimalsScreen(
            viewModel = viewModel,
            onAnimalClick = { id ->
                navController.navigate(Screen.AnimalDetailsScreen.createRoute(id))
            },
            onAddAnimalClick = {
                navController.navigate(AnimalFormRoutes.ADD) // Navigates to the form
            }
        )
    }
}