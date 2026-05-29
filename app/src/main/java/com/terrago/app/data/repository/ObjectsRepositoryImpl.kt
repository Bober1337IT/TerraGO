package com.terrago.app.data.repository

import com.terrago.app.data.local.dao.ObjectsDao
import com.terrago.app.data.local.mapper.toDomain
import com.terrago.app.data.local.mapper.toEntity
import com.terrago.app.domain.objects.ObjectsRepository
import com.terrago.app.domain.objects.model.TerraObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObjectsRepositoryImpl @Inject constructor(
    private val dao: ObjectsDao
) : ObjectsRepository {

    override fun getAllObjects(): Flow<List<TerraObject>> =
        dao.observeAll().map { rows -> rows.map { it.toDomain() } }

    override fun getObjectById(id: Long): Flow<TerraObject?> =
        dao.observeById(id).map { it?.toDomain() }

    override suspend fun insertObject(obj: TerraObject): Long =
        dao.insert(obj.toEntity())

    override suspend fun updateObject(obj: TerraObject) {
        dao.update(obj.toEntity())
    }

    override suspend fun deleteObject(objectId: Long) = dao.delete(objectId)
}
