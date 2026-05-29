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
            childColumns = ["calendarEventId"]
        )
    ],
    indices = [Index("calendarEventId")]
)
data class ScheduledNotificationEntity(
    @PrimaryKey(autoGenerate = true) val notificationId: Long = 0L,
    val calendarEventId: Long,
    val requestCode: Long,
    val channelId: String,
    val nextTriggerMillis: Long,
    val isActive: Boolean
)
