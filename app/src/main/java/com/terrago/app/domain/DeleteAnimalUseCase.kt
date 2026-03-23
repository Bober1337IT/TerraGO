package com.terrago.app.domain

import com.terrago.app.database.repositories.AnimalsRepository

class DeleteAnimalUseCase(
    private val animalsRepository: AnimalsRepository
) {
    operator fun invoke(animalId: Long) {
        animalsRepository.deleteAnimal(animalId)
    }
}