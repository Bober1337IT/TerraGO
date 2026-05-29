package com.terrago.app.domain.species

import com.terrago.app.domain.species.model.Species
import kotlinx.coroutines.flow.Flow

interface SpeciesRepository {

    fun getAllSpecies(): Flow<List<Species>>

    fun getSpeciesById(id: Long): Flow<Species?>

    suspend fun countSpecies(): Int

    suspend fun insertSpecies(species: Species): Long

    suspend fun updateSpecies(species: Species)

    suspend fun insertAllSpecies(species: List<Species>)
}
