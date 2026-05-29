package com.terrago.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "species")
data class SpeciesEntity(
    @PrimaryKey(autoGenerate = true) val speciesId: Long = 0L,
    val nameLatin: String,
    val nameCommon: String?,
    val description: String?,
    val temperatureMin: Double?,
    val temperatureMax: Double?,
    val humidityMin: Double?,
    val humidityMax: Double?,
    val lightCycleH: Long?
)
