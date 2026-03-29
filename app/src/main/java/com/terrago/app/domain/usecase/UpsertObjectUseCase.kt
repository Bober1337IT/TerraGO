package com.terrago.app.domain.usecase

import com.terrago.app.domain.repository.ObjectsRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UpsertObjectUseCase @Inject constructor(
    private val repository: ObjectsRepository
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
            repository.insertObject(
                name = name,
                description = description,
                length = length,
                width = width,
                height = height,
                locationName = location
            )

            // Explicitly fetch latest to select it
            repository.getAllObjects()
                .first()
                .maxByOrNull { it.object_id }
                ?.object_id ?: -1L

        } else {
            repository.updateObject(
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