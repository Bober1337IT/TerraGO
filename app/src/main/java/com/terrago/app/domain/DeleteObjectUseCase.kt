package com.terrago.app.domain

import com.terrago.app.database.repositories.ObjectsRepository

class DeleteObjectUseCase(
    private val objectsRepository: ObjectsRepository
) {
    operator fun invoke(objectId: Long) {
        objectsRepository.deleteObject(objectId)
    }
}