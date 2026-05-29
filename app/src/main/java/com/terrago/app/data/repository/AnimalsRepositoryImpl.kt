package com.terrago.app.data.repository

import com.terrago.app.data.local.dao.AnimalsDao
import com.terrago.app.data.local.mapper.toDomain
import com.terrago.app.data.local.mapper.toEntity
import com.terrago.app.domain.animals.AnimalsRepository
import com.terrago.app.domain.animals.model.Animal
import com.terrago.app.domain.animals.model.AnimalDetails
import com.terrago.app.domain.animals.model.AnimalPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnimalsRepositoryImpl @Inject constructor(
    private val dao: AnimalsDao
) : AnimalsRepository {

    override fun getAnimalsPreview(): Flow<List<AnimalPreview>> =
        dao.observeAnimalsWithDetails().map { rows -> rows.map { it.toDomain() } }

    override fun getAnimalsDetailsById(animalId: Long): Flow<AnimalDetails?> =
        dao.observeAnimalDetailsById(animalId).map { it?.toDomain() }

    override fun getAnimalById(animalId: Long): Flow<Animal?> =
        dao.observeById(animalId).map { it?.toDomain() }

    override suspend fun insertAnimal(animal: Animal): Long =
        dao.insert(animal.toEntity())

    override suspend fun updateAnimal(animal: Animal) {
        dao.update(animal.toEntity())
    }

    override suspend fun setLastFeeding(animalId: Long) = dao.setLastFeeding(animalId)

    override suspend fun setLastSpray(animalId: Long) = dao.setLastSpray(animalId)

    override suspend fun setLastMolt(animalId: Long) = dao.setLastMolt(animalId)

    override suspend fun setSize(animalId: Long, size: Long) = dao.setSize(animalId, size)

    override suspend fun deleteAnimal(animalId: Long) = dao.delete(animalId)
}
