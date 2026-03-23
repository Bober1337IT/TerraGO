package com.terrago.app.domain.usecase

import com.terrago.app.data.repositories.AnimalsRepository

class DeleteAnimalUseCase(
    private val animalsRepository: AnimalsRepository
) {
    operator fun invoke(animalId: Long) {
        animalsRepository.deleteAnimal(animalId)
    }
}