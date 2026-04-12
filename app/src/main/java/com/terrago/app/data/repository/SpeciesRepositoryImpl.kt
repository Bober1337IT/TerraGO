package com.terrago.app.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.terrago.app.db.Species
import com.terrago.app.db.TerraGoDatabase
import com.terrago.app.domain.species.SpeciesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class SpeciesRepositoryImpl @Inject constructor(
    private val db: TerraGoDatabase
) : SpeciesRepository {

    override fun getAllSpecies(): Flow<List<Species>> {
        return db.speciesQueries
            .getAllSpecies()
            .asFlow()
            .mapToList(context = Dispatchers.IO)
    }

    override fun getSpeciesById(id: Long): Flow<Species?> {
        return db.speciesQueries
            .getSpeciesById(id)
            .asFlow()
            .mapToList(context = Dispatchers.IO)
            .map { it.firstOrNull() }
    }

    override fun insertSpecies(
        nameLatin: String,
        nameCommon: String?,
        description: String?,
        temperatureMin: Double?,
        temperatureMax: Double?,
        humidityMin: Double?,
        humidityMax: Double?,
        lightCycleH: Long?
    ) {
        db.speciesQueries.insertSpecies(
            nameLatin,
            nameCommon,
            description,
            temperatureMin,
            temperatureMax,
            humidityMin,
            humidityMax,
            lightCycleH
        )
    }
}