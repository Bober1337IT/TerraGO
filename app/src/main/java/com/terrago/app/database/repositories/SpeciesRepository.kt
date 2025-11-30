package com.terrago.app.database.repositories

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.terrago.app.db.Species
import com.terrago.app.db.TerraGoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SpeciesRepository(private val db: TerraGoDatabase) {

    fun getAllSpecies(): Flow<List<Species>> {
        return db.speciesQueries
            .getAllSpecies()
            .asFlow()
            .mapToList(context = Dispatchers.IO)
    }

    fun getSpeciesById(id: Long): Flow<Species?> {
        return db.speciesQueries
            .getSpeciesById(id)
            .asFlow()
            .mapToList(context = Dispatchers.IO)
            .map { it.firstOrNull() }
    }

    suspend fun insertSpecies(
        nameLatin: String? = null,
        nameCommon: String,
        description: String? = null,
        temperatureMin: Double? = null,
        temperatureMax: Double? = null,
        humidityMin: Double? = null,
        humidityMax: Double? = null,
        lightCycleH: Long? = null
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