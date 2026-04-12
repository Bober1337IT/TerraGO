package com.terrago.app.domain.animals.usecase

import com.terrago.app.domain.animals.AnimalsRepository
import javax.inject.Inject

class DeleteAnimalUseCase @Inject constructor(
    private val repository: AnimalsRepository
) {
    operator fun invoke(animalId: Long) {
        repository.deleteAnimal(animalId)
    }
}