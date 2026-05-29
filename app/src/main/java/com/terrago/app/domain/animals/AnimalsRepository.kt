package com.terrago.app.domain.animals

import com.terrago.app.domain.animals.model.Animal
import com.terrago.app.domain.animals.model.AnimalDetails
import com.terrago.app.domain.animals.model.AnimalPreview
import kotlinx.coroutines.flow.Flow

interface AnimalsRepository {

    fun getAnimalsPreview(): Flow<List<AnimalPreview>>

    fun getAnimalsDetailsById(animalId: Long): Flow<AnimalDetails?>

    fun getAnimalById(animalId: Long): Flow<Animal?>

    suspend fun insertAnimal(animal: Animal): Long

    suspend fun updateAnimal(animal: Animal)

    suspend fun setLastFeeding(animalId: Long)
    suspend fun setLastSpray(animalId: Long)
    suspend fun setLastMolt(animalId: Long)
    suspend fun setSize(animalId: Long, size: Long)
    suspend fun deleteAnimal(animalId: Long)
}
