package com.terrago.app.domain

import com.terrago.app.database.repositories.ObjectsRepository
import kotlinx.coroutines.flow.first

class UpsertObjectUseCase(
    private val objectsRepository: ObjectsRepository
) {

    suspend operator fun invoke(
        objectId: Long?,
        name: String,
        description: String?,
        length: Long?,
        width: Long?,
        height: Long?,
        location: String?
    ): Long {
        return if (objectId == null) {
            objectsRepository.insertObject(
                name = name,
                description = description,
                length = length,
                width = width,
                height = height,
                locationName = location
            )

            objectsRepository.getAllObjects()
                .first()
                .maxByOrNull { it.object_id }
                ?.object_id ?: -1L

        } else {
            objectsRepository.updateObject(
                objectId = objectId,
                name = name,
                description = description,
                length = length,
                width = width,
                height = height,
                locationName = location
            )

            objectId // after update we return the same id
        }
    }
}