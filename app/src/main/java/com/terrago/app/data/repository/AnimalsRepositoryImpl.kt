package com.terrago.app.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.terrago.app.data.local.entity.AnimalDetails
import com.terrago.app.data.local.entity.AnimalPreview
import com.terrago.app.db.Animals
import com.terrago.app.db.TerraGoDatabase
import com.terrago.app.domain.animals.AnimalsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class AnimalsRepositoryImpl @Inject constructor(
    private val db: TerraGoDatabase
) : AnimalsRepository {
    override fun getAnimalsPreview(): Flow<List<AnimalPreview>> {
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

    override fun getAnimalsDetailsById(animalId: Long): Flow<AnimalDetails?> {
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

    override fun insertAnimal(
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

    override fun updateAnimal(
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

    override fun setLastFeeding(animalId: Long) {
        db.animalsQueries.setLastFeeding(animalId)
    }

    override fun setLastSpray(animalId: Long) {
        db.animalsQueries.setLastSpray(animalId)
    }

    override fun setLastMolt(animalId: Long) {
        db.animalsQueries.setLastMolt(animalId)
    }

    override fun setSize(animalId: Long, size: Long) {
        db.animalsQueries.setSize(size, animalId)
    }

    override fun deleteAnimal(animalId: Long) {
        db.animalsQueries.deleteAnimal(animalId)
    }

    override fun getAnimalById(id: Long): Flow<Animals?> {
        return db.animalsQueries
            .getAnimalsById(id)
            .asFlow()
            .mapToList(context = Dispatchers.IO)
            .map { it.firstOrNull() }
    }
}
