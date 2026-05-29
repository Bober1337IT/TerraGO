package com.terrago.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.terrago.app.data.local.entity.SpeciesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpeciesDao {

    @Query("SELECT * FROM species")
    fun observeAll(): Flow<List<SpeciesEntity>>

    @Query("SELECT * FROM species WHERE speciesId = :id")
    fun observeById(id: Long): Flow<SpeciesEntity?>

    @Query("SELECT COUNT(*) FROM species")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(species: SpeciesEntity): Long

    @Update
    suspend fun update(species: SpeciesEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(species: List<SpeciesEntity>): List<Long>
}
