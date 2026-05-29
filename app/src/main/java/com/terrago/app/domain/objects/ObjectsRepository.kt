package com.terrago.app.domain.objects

import com.terrago.app.domain.objects.model.TerraObject
import kotlinx.coroutines.flow.Flow

interface ObjectsRepository {

    fun getAllObjects(): Flow<List<TerraObject>>

    fun getObjectById(id: Long): Flow<TerraObject?>

    suspend fun insertObject(obj: TerraObject): Long

    suspend fun updateObject(obj: TerraObject)

    suspend fun deleteObject(objectId: Long)
}
