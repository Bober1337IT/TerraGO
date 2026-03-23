package com.terrago.app.domain

import com.terrago.app.database.repositories.AnimalsRepository

class UpsertAnimalUseCase(
    private val animalsRepository: AnimalsRepository
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
            animalsRepository.insertAnimal(
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
            animalsRepository.updateAnimal(
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