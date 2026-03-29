package com.terrago.app.domain.repository

import com.terrago.app.db.Species
import kotlinx.coroutines.flow.Flow

interface SpeciesRepository {

    fun getAllSpecies(): Flow<List<Species>>
    fun getSpeciesById(id: Long): Flow<Species?>

    fun insertSpecies(
        nameLatin: String,
        nameCommon: String? = null,
        description: String? = null,
        temperatureMin: Double? = null,
        temperatureMax: Double? = null,
        humidityMin: Double? = null,
        humidityMax: Double? = null,
        lightCycleH: Long? = null
    )
}