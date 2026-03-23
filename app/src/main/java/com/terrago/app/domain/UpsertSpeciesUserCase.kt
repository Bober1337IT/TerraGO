package com.terrago.app.domain

import com.terrago.app.database.repositories.SpeciesRepository
import kotlinx.coroutines.flow.first

class UpsertSpeciesUserCase(
    private val speciesRepository: SpeciesRepository
) {
    suspend operator fun invoke(
        latinName: String,
        commonName: String?,
        description: String?,
        temperatureMin: Double?,
        temperatureMax: Double?,
        humidityMin: Double?,
        humidityMax: Double?,
        lightCycleH: Long?
    ): Long {
        speciesRepository.insertSpecies(
            nameLatin = latinName,
            nameCommon = commonName,
            description = description,
            temperatureMin = temperatureMin,
            temperatureMax = temperatureMax,
            humidityMin = humidityMin,
            humidityMax = humidityMax,
            lightCycleH = lightCycleH
        )

        // Explicitly fetch latest to select it
        return speciesRepository.getAllSpecies()
            .first()
            .maxByOrNull { it.species_id }
            ?.species_id ?: -1L
    }
}