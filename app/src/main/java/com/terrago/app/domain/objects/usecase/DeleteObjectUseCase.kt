package com.terrago.app.domain.objects.usecase

import com.terrago.app.domain.objects.ObjectsRepository
import javax.inject.Inject

class DeleteObjectUseCase @Inject constructor(
    private val repository: ObjectsRepository
) {
    operator fun invoke(objectId: Long) {
        repository.deleteObject(objectId)
    }
}