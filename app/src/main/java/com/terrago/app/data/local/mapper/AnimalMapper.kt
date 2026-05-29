package com.terrago.app.data.local.mapper

import com.terrago.app.data.local.dao.projection.AnimalDetailsProjection
import com.terrago.app.data.local.dao.projection.AnimalPreviewProjection
import com.terrago.app.data.local.entity.AnimalEntity
import com.terrago.app.domain.animals.model.Animal
import com.terrago.app.domain.animals.model.AnimalDetails
import com.terrago.app.domain.animals.model.AnimalPreview

internal fun AnimalEntity.toDomain(): Animal = Animal(
    id = animalId,
    objectId = objectId,
    speciesId = speciesId,
    name = name,
    gender = gender,
    birthDate = birthDate,
    lastFeeding = lastFeeding,
    lastSpray = lastSpray,
    lastMolt = lastMolt,
    size = size,
    sizeType = sizeType,
    notes = notes,
    photo = photo
)

internal fun Animal.toEntity(): AnimalEntity = AnimalEntity(
    animalId = id,
    objectId = objectId,
    speciesId = speciesId,
    name = name,
    gender = gender,
    birthDate = birthDate,
    lastFeeding = lastFeeding,
    lastSpray = lastSpray,
    lastMolt = lastMolt,
    size = size,
    sizeType = sizeType,
    notes = notes,
    photo = photo
)

internal fun AnimalPreviewProjection.toDomain(): AnimalPreview = AnimalPreview(
    animalId = animalId,
    animalName = animalName,
    speciesLatinName = speciesLatinName,
    objectName = objectName,
    lastFeeding = lastFeeding,
    lastSpray = lastSpray,
    lastMolt = lastMolt,
    size = size,
    sizeType = sizeType,
    photo = photo
)

internal fun AnimalDetailsProjection.toDomain(): AnimalDetails = AnimalDetails(
    animalId = animalId,
    animalName = animalName,
    speciesLatinName = speciesLatinName,
    speciesCommonName = speciesCommonName,
    speciesDescription = speciesDescription,
    speciesTempMin = speciesTempMin,
    speciesTempMax = speciesTempMax,
    speciesHumMin = speciesHumMin,
    speciesHumMax = speciesHumMax,
    speciesLightCycle = speciesLightCycle,
    objectName = objectName,
    objectDescription = objectDescription,
    objectLength = objectLength,
    objectWidth = objectWidth,
    objectHeight = objectHeight,
    objectLocation = objectLocation,
    lastFeeding = lastFeeding,
    lastSpray = lastSpray,
    lastMolt = lastMolt,
    birthDate = birthDate,
    gender = gender,
    size = size,
    sizeType = sizeType,
    notes = notes,
    photo = photo
)
