package com.terrago.app.domain.usecase

import com.terrago.app.domain.repository.ObjectsRepository
import javax.inject.Inject

class DeleteObjectUseCase @Inject constructor(
    private val repository: ObjectsRepository
) {
    operator fun invoke(objectId: Long) {
        repository.deleteObject(objectId)
    }
}