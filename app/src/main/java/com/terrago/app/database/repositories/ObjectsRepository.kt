package com.terrago.app.database.repositories

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.terrago.app.db.Objects
import com.terrago.app.db.TerraGoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObjectsRepository(private val db: TerraGoDatabase) {

    fun getAllObjects(): Flow<List<Objects>>{
        return db.objectsQueries
            .getAllObjects()
            .asFlow()
            .mapToList(context = Dispatchers.IO)
    }

    fun getObjectById(id: Long): Flow<Objects?>{
        return db.objectsQueries
            .getObjectById(id)
            .asFlow()
            .mapToList(context = Dispatchers.IO)
            .map {it.firstOrNull()}
    }

    suspend fun insertObject(
        name: String,
        description: String? = null,
        length: Long? = null,
        width: Long? = null,
        height: Long? = null,
        locationName: String? = null
    ){
        db.objectsQueries.insertObject(
            name,
            description,
            length,
            width,
            height,
            locationName
        )
    }

}