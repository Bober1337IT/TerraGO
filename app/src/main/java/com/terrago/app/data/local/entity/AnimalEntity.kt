package com.terrago.app.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "animals",
    foreignKeys = [
        ForeignKey(
            entity = ObjectEntity::class,
            parentColumns = ["objectId"],
            childColumns = ["objectId"]
        ),
        ForeignKey(
            entity = SpeciesEntity::class,
            parentColumns = ["speciesId"],
            childColumns = ["speciesId"]
        )
    ],
    indices = [Index("objectId"), Index("speciesId")]
)
data class AnimalEntity(
    @PrimaryKey(autoGenerate = true) val animalId: Long = 0L,
    val objectId: Long,
    val speciesId: Long,
    val name: String?,
    val gender: String?,
    val birthDate: String?,
    val lastFeeding: String?,
    val lastSpray: String?,
    val lastMolt: String?,
    val size: Long?,
    val sizeType: Long?,
    val notes: String?,
    val photo: ByteArray?
)
