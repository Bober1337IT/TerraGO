package com.terrago.app.domain.usecase

import com.terrago.app.data.repositories.ObjectsRepository

class DeleteObjectUseCase(
    private val objectsRepository: ObjectsRepository
) {
    operator fun invoke(objectId: Long) {
        objectsRepository.deleteObject(objectId)
    }
}