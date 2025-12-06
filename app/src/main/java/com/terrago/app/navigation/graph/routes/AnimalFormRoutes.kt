package com.terrago.app.navigation.graph.routes

object AnimalFormRoutes {
    const val ADD = "animalForm"
    const val EDIT = "animalForm/{animalId}"

    fun edit(id: Long) = "animalForm/$id"
}