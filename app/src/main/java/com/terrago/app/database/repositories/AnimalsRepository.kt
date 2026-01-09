package com.terrago.app.database.repositories

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.terrago.app.database.entity.AnimalDetails
import com.terrago.app.database.entity.AnimalPreview
import com.terrago.app.db.Animals
import com.terrago.app.db.TerraGoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class AnimalsRepository(private val db: TerraGoDatabase) {

    fun getAnimalsPreview(): Flow<List<AnimalPreview>> {
        return db.animalsQueries
            .getAnimalsWithDetails()
            .asFlow()
            .mapToList(context = Dispatchers.IO)
            .map { list ->
                list.map { row ->
                    AnimalPreview(
                        animalId = row.animal_id,
                        animalName = row.animalName,
                        speciesLatinName = row.speciesLatinName,
                        objectName = row.objectName,
                        lastFeeding = row.last_feeding,
                        lastSpray = row.last_spray,
                        lastMolt = row.last_molt,
                        size = row.size,
                        sizeType = row.size_type,
                        photo = row.photo
                    )
                }
            }
    }

    fun getAnimalsDetailsById(animalId: Long): Flow<AnimalDetails?> {
        return db.animalsQueries
            .getAnimalsWithDetailsById(animalId)
            .asFlow()
            .mapToList(context = Dispatchers.IO)
            .map { list ->
                list.firstOrNull()?.let { row ->
                    AnimalDetails(
                        animalId = row.animal_id,
                        animalName = row.animalName,
                        speciesLatinName = row.speciesLatinName,
                        speciesCommonName = row.speciesCommonName,
                        speciesDescription = row.speciesDescription,
                        speciesTempMin = row.speciesTempMin,
                        speciesTempMax = row.speciesTempMax,
                        speciesHumMin = row.speciesHumMin,
                        speciesHumMax = row.speciesHumMax,
                        speciesLightCycle = row.speciesLightCycle,
                        objectName = row.objectName,
                        objectDescription = row.objectDescription,
                        objectLength = row.objectLength,
                        objectWidth = row.objectWidth,
                        objectHeight = row.objectHeight,
                        objectLocation = row.objectLocation,
                        lastFeeding = row.last_feeding,
                        lastSpray = row.last_spray,
                        lastMolt = row.last_molt,
                        birthDate = row.birth_date,
                        gender = row.gender,
                        size = row.size,
                        sizeType = row.size_type,
                        notes = row.notes,
                        photo = row.photo
                    )
                }
            }
    }

    suspend fun insertAnimal(
        objectId: Long,
        speciesId: Long,
        name: String? = null,
        gender: String? = null,
        birthDate: String? = null,
        lastFeeding: String? = null,
        lastSpray: String? = null,
        lastMolt: String? = null,
        size: Long? = null,
        sizeType: Long? = null,
        notes: String? = null,
        photo: ByteArray? = null
    ) {
        db.animalsQueries.insertAnimal(
            objectId,
            speciesId,
            name,
            gender,
            birthDate,
            lastFeeding,
            lastSpray,
            lastMolt,
            size,
            sizeType,
            notes,
            photo
        )
    }

    suspend fun updateAnimal(
        animalId: Long,
        objectId: Long,
        speciesId: Long,
        name: String?,
        gender: String?,
        birthDate: String?,
        lastFeeding: String?,
        lastSpray: String?,
        lastMolt: String?,
        size: Long?,
        sizeType: Long?,
        notes: String?,
        photo: ByteArray?
    ) {
        db.animalsQueries.updateAnimal(
            objectId,
            speciesId,
            name,
            gender,
            birthDate,
            lastFeeding,
            lastSpray,
            lastMolt,
            size,
            sizeType,
            notes,
            photo,
            animalId
        )
    }

    suspend fun setLastFeeding(animalId: Long) {
        db.animalsQueries.setLastFeeding(animalId)
    }

    suspend fun setLastSpray(animalId: Long) {
        db.animalsQueries.setLastSpray(animalId)
    }

    suspend fun setLastMolt(animalId: Long) {
        db.animalsQueries.setLastMolt(animalId)
    }

    suspend fun setSize(animalId: Long, size: Long) {
        db.animalsQueries.setSize(size, animalId)
    }

    suspend fun deleteAnimal(animalId: Long) {
        db.animalsQueries.deleteAnimal(animalId)
    }

    fun getAnimalById(id: Long): Flow<Animals?> {
        return db.animalsQueries
            .getAnimalsById(id)
            .asFlow()
            .mapToList(context = Dispatchers.IO)
            .map { it.firstOrNull() }
    }
}
