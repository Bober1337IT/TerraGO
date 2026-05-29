package com.terrago.app.domain.objects.usecase

import com.terrago.app.domain.objects.ObjectsRepository
import com.terrago.app.domain.objects.model.TerraObject
import javax.inject.Inject

class UpsertObjectUseCase @Inject constructor(
    private val repository: ObjectsRepository
) {
    suspend operator fun invoke(obj: TerraObject): Long =
        if (obj.id == 0L) {
            repository.insertObject(obj)
        } else {
            repository.updateObject(obj)
            obj.id
        }
}
