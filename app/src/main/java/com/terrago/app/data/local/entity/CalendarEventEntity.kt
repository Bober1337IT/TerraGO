package com.terrago.app.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "calendar_events",
    foreignKeys = [
        ForeignKey(
            entity = ObjectEntity::class,
            parentColumns = ["objectId"],
            childColumns = ["objectId"]
        )
    ],
    indices = [Index("objectId")]
)
data class CalendarEventEntity(
    @PrimaryKey(autoGenerate = true) val calendarEventId: Long = 0L,
    val objectId: Long,
    val title: String,
    val description: String?,
    val eventDate: String,
    val hasReminder: Boolean,
    val reminderOffsetMinutes: Long
)
