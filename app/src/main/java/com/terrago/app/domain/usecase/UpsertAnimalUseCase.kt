package com.terrago.app.domain.usecase

import com.terrago.app.domain.repository.AnimalsRepository
import javax.inject.Inject

class UpsertAnimalUseCase @Inject constructor(
    private val repository: AnimalsRepository
) {

    operator fun invoke(
        animalId: Long?,
        objectId: Long,
        speciesId: Long,
        name: String?,
        gender: String?,
        birthDate: String?,
        lastFeeding: String?,
        lastSpray: String?,
        lastMolt: String?,
        size: Long?,
        sizeType: Long?,
        notes: String?,
        photo: ByteArray?
    ) {
        if (animalId == null) {
            repository.insertAnimal(
                objectId = objectId,
                speciesId = speciesId,
                name = name,
                gender = gender,
                birthDate = birthDate,
                lastFeeding = lastFeeding,
                lastSpray = lastSpray,
                lastMolt = lastMolt,
                size = size,
                sizeType = sizeType,
                notes = notes,
                photo = photo
            )
        } else {
            repository.updateAnimal(
                animalId = animalId,
                objectId = objectId,
                speciesId = speciesId,
                name = name,
                gender = gender,
                birthDate = birthDate,
                lastFeeding = lastFeeding,
                lastSpray = lastSpray,
                lastMolt = lastMolt,
                size = size,
                sizeType = sizeType,
                notes = notes,
                photo = photo
            )
        }
    }
}