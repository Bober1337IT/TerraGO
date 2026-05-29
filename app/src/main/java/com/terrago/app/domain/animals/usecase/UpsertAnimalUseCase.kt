package com.terrago.app.domain.animals.usecase

import com.terrago.app.domain.animals.AnimalsRepository
import com.terrago.app.domain.animals.model.Animal
import javax.inject.Inject

class UpsertAnimalUseCase @Inject constructor(
    private val repository: AnimalsRepository
) {
    suspend operator fun invoke(animal: Animal): Long =
        if (animal.id == 0L) {
            repository.insertAnimal(animal)
        } else {
            repository.updateAnimal(animal)
            animal.id
        }
}
