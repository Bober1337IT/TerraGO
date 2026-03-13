package com.terrago.app.viewmodel.animalformviewmodel

import com.terrago.app.db.Species

data class AnimalFormUiState(

    // Animal fields
    val name: String = "",
    val selectedObject: Long? = null,
    val selectedSpecies: Long? = null,
    val birthDate: String = "",
    val gender: String = "",
    val size: String = "",
    val sizeType: Long = 0L,
    val notes: String = "",
    val photo: ByteArray? = null,

    // Species fields
    val speciesLatinName: String = "",
    val speciesCommonName: String = "",
    val speciesDescription: String = "",
    val speciesTempMin: String = "",
    val speciesTempMax: String = "",
    val speciesHumMin: String = "",
    val speciesHumMax: String = "",
    val speciesLightCycle: String = "",

    // Selection state
    val speciesSearchQuery: String = "",
    val filteredSpecies: List<Species> = emptyList(),

    // Object fields
    val objectName: String = "",
    val objectLocationName: String = "",
    val objectDescription: String = "",
    val objectLength: String = "",
    val objectWidth: String = "",
    val objectHeight: String = ""
)
