package com.terrago.app.domain.usecase

import com.terrago.app.domain.repository.AnimalsRepository
import javax.inject.Inject

class DeleteAnimalUseCase @Inject constructor(
    private val repository: AnimalsRepository
) {
    operator fun invoke(animalId: Long) {
        repository.deleteAnimal(animalId)
    }
}