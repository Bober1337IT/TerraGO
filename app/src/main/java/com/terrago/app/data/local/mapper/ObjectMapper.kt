package com.terrago.app.data.local.mapper

import com.terrago.app.data.local.entity.ObjectEntity
import com.terrago.app.domain.objects.model.TerraObject

internal fun ObjectEntity.toDomain(): TerraObject = TerraObject(
    id = objectId,
    name = name,
    description = description,
    length = length,
    width = width,
    height = height,
    locationName = locationName
)

internal fun TerraObject.toEntity(): ObjectEntity = ObjectEntity(
    objectId = id,
    name = name,
    description = description,
    length = length,
    width = width,
    height = height,
    locationName = locationName
)
