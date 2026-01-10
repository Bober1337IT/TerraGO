package com.terrago.app.navigation

sealed class Screen(val route: String) {

    data object AnimalsScreen : Screen("animals")

    data object AnimalDetailsScreen : Screen("animal_details/{animalId}") {
        fun createRoute(id: Long) = "animal_details/$id"
    }

    data object AnimalFormRoutes {
        const val ADD = "animalForm"
        const val EDIT = "animalForm/{animalId}"
        const val NEW_HABITAT = "new_habitat"
        const val NEW_SPECIES = "new_species"

        fun edit(id: Long) = "animalForm/$id"
    }
}