package com.terrago.app.database.entity

data class AnimalPreview(
    val animalId: Long,
    val animalName: String?,
    val speciesLatinName: String?,
    val objectName: String?,
    val lastFeeding: String?,
    val lastSpray: String?,
    val lastMolt: String?,
    val photo: ByteArray?
)