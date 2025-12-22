package com.terrago.app.navigation.graph.routes

object AnimalFormRoutes {
    const val ADD = "animalForm"
    const val EDIT = "animalForm/{animalId}"
    const val NEW_HABITAT = "new_habitat"
    const val NEW_SPECIES = "new_species"

    fun edit(id: Long) = "animalForm/$id"
}