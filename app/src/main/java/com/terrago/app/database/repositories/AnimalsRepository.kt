package com.terrago.app.database.repositories

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.terrago.app.database.entity.AnimalPreview
import com.terrago.app.db.Animals
import com.terrago.app.db.TerraGoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AnimalsRepository(private val db: TerraGoDatabase) {

    fun getAllAnimals(): Flow<List<Animals>>{
        return db.animalsQueries
            .getAllAnimals()
            .asFlow()
            .mapToList(context = Dispatchers.IO)
    }

    fun getAnimalById(id: Long): Flow<Animals?>{
        return db.animalsQueries
            .getAnimalsById(id)
            .asFlow()
            .mapToList(context = Dispatchers.IO)
            .map {it.firstOrNull()}
    }

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
                        photo = row.photo
                    )
                }
            }
    }

    // For testing purposes
    fun deleteAllAnimals() {
        db.animalsQueries.deleteAllAnimals()
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
}