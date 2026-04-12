package com.terrago.app.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.terrago.app.db.Objects
import com.terrago.app.db.TerraGoDatabase
import com.terrago.app.domain.objects.ObjectsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObjectsRepositoryImpl @Inject constructor(
    private val db: TerraGoDatabase
) : ObjectsRepository {

    override fun getAllObjects(): Flow<List<Objects>> {
        return db.objectsQueries
            .getAllObjects()
            .asFlow()
            .mapToList(context = Dispatchers.IO)
    }

    override fun getObjectById(id: Long): Flow<Objects?> {
        return db.objectsQueries
            .getObjectById(id)
            .asFlow()
            .mapToList(context = Dispatchers.IO)
            .map { it.firstOrNull() }
    }

    override fun insertObject(
        name: String,
        description: String?,
        length: Long?,
        width: Long?,
        height: Long?,
        locationName: String?
    ) {
        db.objectsQueries.insertObject(
            name,
            description,
            length,
            width,
            height,
            locationName
        )
    }

    override fun updateObject(
        objectId: Long,
        name: String,
        description: String?,
        length: Long?,
        width: Long?,
        height: Long?,
        locationName: String?
    ) {
        db.objectsQueries.updateObject(
            name,
            description,
            length,
            width,
            height,
            locationName,
            objectId
        )
    }

    override fun deleteObject(objectId: Long) {
        db.objectsQueries.deleteObject(objectId)
    }

}
