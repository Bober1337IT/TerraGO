package com.terrago.app.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.terrago.app.presentation.navigation.graph.animalFormGraph
import com.terrago.app.presentation.navigation.graph.animalDetailsGraph
import com.terrago.app.presentation.navigation.graph.animalsGraph
import com.terrago.app.presentation.viewmodel.animalformviewmodel.AnimalFormViewModel
import com.terrago.app.presentation.viewmodel.animalsviewmodel.AnimalsViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    val animalsViewModel: AnimalsViewModel = hiltViewModel()
    val animalFormViewModel: AnimalFormViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.AnimalsScreen.route
    ) {
        animalsGraph(animalsViewModel, navController)
        animalDetailsGraph(animalsViewModel, navController)
        animalFormGraph(animalFormViewModel, navController)
    }
}