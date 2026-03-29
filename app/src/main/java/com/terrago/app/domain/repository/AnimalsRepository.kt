package com.terrago.app.domain.repository

import com.terrago.app.data.database.entity.AnimalDetails
import com.terrago.app.data.database.entity.AnimalPreview
import com.terrago.app.db.Animals
import kotlinx.coroutines.flow.Flow

interface AnimalsRepository {
    fun getAnimalsPreview(): Flow<List<AnimalPreview>>
    fun getAnimalsDetailsById(animalId: Long): Flow<AnimalDetails?>

    fun insertAnimal(
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
    )

    fun updateAnimal(
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
    )

    fun setLastFeeding(animalId: Long)
    fun setLastSpray(animalId: Long)
    fun setLastMolt(animalId: Long)
    fun setSize(animalId: Long, size: Long)
    fun deleteAnimal(animalId: Long)
    fun getAnimalById(id: Long): Flow<Animals?>
}