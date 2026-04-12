package com.terrago.app.domain.objects

import com.terrago.app.db.Objects
import kotlinx.coroutines.flow.Flow

interface ObjectsRepository {
    fun getAllObjects(): Flow<List<Objects>>
    fun getObjectById(id: Long): Flow<Objects?>

    fun insertObject(
        name: String,
        description: String? = null,
        length: Long? = null,
        width: Long? = null,
        height: Long? = null,
        locationName: String? = null
    )

    fun updateObject(
        objectId: Long,
        name: String,
        description: String? = null,
        length: Long? = null,
        width: Long? = null,
        height: Long? = null,
        locationName: String? = null
    )

    fun deleteObject(objectId: Long)
}