package com.terrago.app.database.repositories

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.terrago.app.database.entity.AnimalDetails
import com.terrago.app.database.entity.AnimalPreview
import com.terrago.app.db.Animals
import com.terrago.app.db.GetAnimalsWithDetails
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
                        objectName = row.objectName,
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

    fun getAnimalById(id: Long): Flow<Animals?> {
        return db.animalsQueries
            .getAnimalsById(id)
            .asFlow()
            .mapToList(context = Dispatchers.IO)
            .map { it.firstOrNull() }
    }
}