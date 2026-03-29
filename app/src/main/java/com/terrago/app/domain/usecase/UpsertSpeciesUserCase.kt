package com.terrago.app.domain.usecase

import com.terrago.app.domain.repository.SpeciesRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UpsertSpeciesUserCase @Inject constructor(
    private val repository: SpeciesRepository
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
        repository.insertSpecies(
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
        return repository.getAllSpecies()
            .first()
            .maxByOrNull { it.species_id }
            ?.species_id ?: -1L
    }
}