package com.terrago.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.terrago.app.data.local.dao.AnimalsDao
import com.terrago.app.data.local.dao.ObjectsDao
import com.terrago.app.data.local.dao.SpeciesDao
import com.terrago.app.data.local.entity.AnimalEntity
import com.terrago.app.data.local.entity.CalendarEventEntity
import com.terrago.app.data.local.entity.ObjectEntity
import com.terrago.app.data.local.entity.ScheduledNotificationEntity
import com.terrago.app.data.local.entity.SpeciesEntity

@Database(
    entities = [
        AnimalEntity::class,
        ObjectEntity::class,
        SpeciesEntity::class,
        CalendarEventEntity::class,
        ScheduledNotificationEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class TerraGoDatabase : RoomDatabase() {
    abstract fun animalsDao(): AnimalsDao
    abstract fun objectsDao(): ObjectsDao
    abstract fun speciesDao(): SpeciesDao
}
