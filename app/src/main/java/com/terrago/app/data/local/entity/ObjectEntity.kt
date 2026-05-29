package com.terrago.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "objects")
data class ObjectEntity(
    @PrimaryKey(autoGenerate = true) val objectId: Long = 0L,
    val name: String,
    val description: String?,
    val length: Long?,
    val width: Long?,
    val height: Long?,
    val locationName: String?
)
