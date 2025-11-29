package com.terrago.app.navigation

sealed class Screen(val route: String) {

    data object AnimalsScreen : Screen("animals")

    data object AnimalDetailsScreen : Screen("animal_details/{animalId}") {
        fun createRoute(id: Long) = "animal_details/$id"
    }
}