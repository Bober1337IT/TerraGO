package com.terrago.app.database.entity

// Needed to show a details of animal
data class AnimalDetails(
    val animalId: Long,
    val animalName: String?,
    val speciesLatinName: String?,
    val speciesCommonName: String?,
    val speciesDescription: String?,
    val speciesTempMin: Double?,
    val speciesTempMax: Double?,
    val speciesHumMin: Double?,
    val speciesHumMax: Double?,
    val speciesLightCycle: Long?,
    val objectName: String?,
    val objectDescription: String?,
    val objectLength: Long?,
    val objectWidth: Long?,
    val objectHeight: Long?,
    val objectLocation: String?,
    val lastFeeding: String?,
    val lastSpray: String?,
    val lastMolt: String?,
    val birthDate: String?,
    val gender: String?,
    val size: Long?,
    val sizeType: Long?,
    val notes: String?,
    val photo: ByteArray?
)
