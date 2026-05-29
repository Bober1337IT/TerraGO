package com.terrago.app.data.repository

import com.terrago.app.data.local.dao.SpeciesDao
import com.terrago.app.data.local.mapper.toDomain
import com.terrago.app.data.local.mapper.toEntity
import com.terrago.app.domain.species.SpeciesRepository
import com.terrago.app.domain.species.model.Species
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SpeciesRepositoryImpl @Inject constructor(
    private val dao: SpeciesDao
) : SpeciesRepository {

    override fun getAllSpecies(): Flow<List<Species>> =
        dao.observeAll().map { rows -> rows.map { it.toDomain() } }

    override fun getSpeciesById(id: Long): Flow<Species?> =
        dao.observeById(id).map { it?.toDomain() }

    override suspend fun countSpecies(): Int = dao.count()

    override suspend fun insertSpecies(species: Species): Long =
        dao.insert(species.toEntity())

    override suspend fun updateSpecies(species: Species) {
        dao.update(species.toEntity())
    }

    override suspend fun insertAllSpecies(species: List<Species>) {
        dao.insertAll(species.map { it.toEntity() })
    }
}
