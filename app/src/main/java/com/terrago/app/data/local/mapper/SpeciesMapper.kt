package com.terrago.app.data.local.mapper

import com.terrago.app.data.local.entity.SpeciesEntity
import com.terrago.app.domain.species.model.Species

internal fun SpeciesEntity.toDomain(): Species = Species(
    id = speciesId,
    nameLatin = nameLatin,
    nameCommon = nameCommon,
    description = description,
    temperatureMin = temperatureMin,
    temperatureMax = temperatureMax,
    humidityMin = humidityMin,
    humidityMax = humidityMax,
    lightCycleH = lightCycleH
)

internal fun Species.toEntity(): SpeciesEntity = SpeciesEntity(
    speciesId = id,
    nameLatin = nameLatin,
    nameCommon = nameCommon,
    description = description,
    temperatureMin = temperatureMin,
    temperatureMax = temperatureMax,
    humidityMin = humidityMin,
    humidityMax = humidityMax,
    lightCycleH = lightCycleH
)
