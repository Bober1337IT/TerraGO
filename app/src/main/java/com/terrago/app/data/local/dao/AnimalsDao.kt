package com.terrago.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.terrago.app.data.local.dao.projection.AnimalDetailsProjection
import com.terrago.app.data.local.dao.projection.AnimalPreviewProjection
import com.terrago.app.data.local.entity.AnimalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalsDao {

    @Query("SELECT * FROM animals WHERE animalId = :id")
    fun observeById(id: Long): Flow<AnimalEntity?>

    @Query(
        """
        SELECT
            a.animalId       AS animalId,
            a.name           AS animalName,
            s.nameLatin      AS speciesLatinName,
            o.name           AS objectName,
            a.lastFeeding    AS lastFeeding,
            a.lastSpray      AS lastSpray,
            a.lastMolt       AS lastMolt,
            a.size           AS size,
            a.sizeType       AS sizeType,
            a.photo          AS photo
        FROM animals a
        JOIN species s ON a.speciesId = s.speciesId
        JOIN objects o ON a.objectId  = o.objectId
        """
    )
    fun observeAnimalsWithDetails(): Flow<List<AnimalPreviewProjection>>

    @Query(
        """
        SELECT
            a.animalId        AS animalId,
            a.name            AS animalName,
            s.nameLatin       AS speciesLatinName,
            s.nameCommon      AS speciesCommonName,
            s.description     AS speciesDescription,
            s.temperatureMin  AS speciesTempMin,
            s.temperatureMax  AS speciesTempMax,
            s.humidityMin     AS speciesHumMin,
            s.humidityMax     AS speciesHumMax,
            s.lightCycleH     AS speciesLightCycle,
            o.name            AS objectName,
            o.description     AS objectDescription,
            o.length          AS objectLength,
            o.width           AS objectWidth,
            o.height          AS objectHeight,
            o.locationName    AS objectLocation,
            a.lastFeeding     AS lastFeeding,
            a.lastSpray       AS lastSpray,
            a.lastMolt        AS lastMolt,
            a.birthDate       AS birthDate,
            a.gender          AS gender,
            a.size            AS size,
            a.sizeType        AS sizeType,
            a.notes           AS notes,
            a.photo           AS photo
        FROM animals a
        JOIN species s ON a.speciesId = s.speciesId
        JOIN objects o ON a.objectId  = o.objectId
        WHERE a.animalId = :id
        """
    )
    fun observeAnimalDetailsById(id: Long): Flow<AnimalDetailsProjection?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(animal: AnimalEntity): Long

    @Update
    suspend fun update(animal: AnimalEntity)

    @Query("UPDATE animals SET lastFeeding = date('now') WHERE animalId = :id")
    suspend fun setLastFeeding(id: Long)

    @Query("UPDATE animals SET lastSpray = date('now') WHERE animalId = :id")
    suspend fun setLastSpray(id: Long)

    @Query(
        """
        UPDATE animals
        SET lastMolt = date('now'),
            size = CASE WHEN sizeType = 1 THEN size + 1 ELSE size END
        WHERE animalId = :id
        """
    )
    suspend fun setLastMolt(id: Long)

    @Query("UPDATE animals SET size = :size WHERE animalId = :id")
    suspend fun setSize(id: Long, size: Long)

    @Query("DELETE FROM animals WHERE animalId = :id")
    suspend fun delete(id: Long)
}
