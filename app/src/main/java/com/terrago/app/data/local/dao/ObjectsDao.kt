package com.terrago.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.terrago.app.data.local.entity.ObjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ObjectsDao {

    @Query("SELECT * FROM objects")
    fun observeAll(): Flow<List<ObjectEntity>>

    @Query("SELECT * FROM objects WHERE objectId = :id")
    fun observeById(id: Long): Flow<ObjectEntity?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(obj: ObjectEntity): Long

    @Update
    suspend fun update(obj: ObjectEntity)

    @Query("DELETE FROM objects WHERE objectId = :id")
    suspend fun delete(id: Long)
}
