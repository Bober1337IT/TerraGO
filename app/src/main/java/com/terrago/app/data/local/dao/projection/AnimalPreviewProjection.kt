package com.terrago.app.data.local.dao.projection

data class AnimalPreviewProjection(
    val animalId: Long,
    val animalName: String?,
    val speciesLatinName: String?,
    val objectName: String?,
    val lastFeeding: String?,
    val lastSpray: String?,
    val lastMolt: String?,
    val size: Long?,
    val sizeType: Long?,
    val photo: ByteArray?
)
