package com.terrago.app.domain.species.model

data class Species(
    val id: Long = 0L,
    val nameLatin: String,
    val nameCommon: String? = null,
    val description: String? = null,
    val temperatureMin: Double? = null,
    val temperatureMax: Double? = null,
    val humidityMin: Double? = null,
    val humidityMax: Double? = null,
    val lightCycleH: Long? = null
)
