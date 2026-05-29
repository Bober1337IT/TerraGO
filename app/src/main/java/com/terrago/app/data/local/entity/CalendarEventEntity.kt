package com.terrago.app.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "calendar_events",
    foreignKeys = [
        ForeignKey(
            entity = AnimalEntity::class,
            parentColumns = ["animalId"],
            childColumns = ["animalId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("animalId")]
)
data class CalendarEventEntity(
    @PrimaryKey(autoGenerate = true) val calendarEventId: Long = 0L, // primary key, auto-generated
    val animalId: Long,                  // owning animal
    val title: String,                   // short event name
    val description: String?,            // optional longer note
    val category: String,                // event type (FEEDING / SPRAYING / VET / OTHER)
    val eventTimeMillis: Long,           // when the event happens (epoch millis)
    val recurrenceIntervalDays: Long?,   // null = one-shot, N = repeat every N days
    val hasReminder: Boolean,            // whether to send a notification
    val reminderOffsetMinutes: Long,     // how many minutes before the event to remind
    val isCompleted: Boolean = false     // marked as done
)
