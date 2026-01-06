package com.terrago.app.database.entity

// Needed to show a preview list of animals
data class AnimalPreview(
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
