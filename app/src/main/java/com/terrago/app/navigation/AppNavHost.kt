package com.terrago.app.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.terrago.app.navigation.graph.addAnimalDetailsGraph
import com.terrago.app.navigation.graph.addAnimalsGraph
import com.terrago.app.viewmodel.AnimalsViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val animalsViewModel: AnimalsViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.AnimalsScreen.route
    ) {
        addAnimalsGraph(animalsViewModel, navController)
        addAnimalDetailsGraph(animalsViewModel, navController)
    }
}