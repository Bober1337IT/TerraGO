package com.terrago.app.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "scheduled_notifications",
    foreignKeys = [
        ForeignKey(
            entity = CalendarEventEntity::class,
            parentColumns = ["calendarEventId"],
            childColumns = ["calendarEventId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("calendarEventId")]
)
data class ScheduledNotificationEntity(
    @PrimaryKey(autoGenerate = true) val notificationId: Long = 0L, // primary key, auto-generated
    val calendarEventId: Long,    // owning calendar event (FK)
    val workName: String,         // unique WorkManager work name
    val channelId: String,        // Android notification channel
    val nextTriggerMillis: Long,  // when WorkManager should fire
    val isActive: Boolean         // still scheduled and waiting
)
