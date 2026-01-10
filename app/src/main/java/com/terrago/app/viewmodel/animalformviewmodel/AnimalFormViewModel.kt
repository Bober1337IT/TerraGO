package com.terrago.app.viewmodel.animalformviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terrago.app.database.repositories.AnimalsRepository
import com.terrago.app.database.repositories.ObjectsRepository
import com.terrago.app.database.repositories.SpeciesRepository
import com.terrago.app.db.Objects
import com.terrago.app.db.Species
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimalFormViewModel(
    private val animalsRepository: AnimalsRepository,
    private val objectsRepository: ObjectsRepository,
    private val speciesRepository: SpeciesRepository
) : ViewModel() {

    // Form state
    var name by mutableStateOf("")
    var selectedObject by mutableStateOf<Long?>(null)
    var selectedSpecies by mutableStateOf<Long?>(null)
    var birthDate by mutableStateOf("")
    var gender by mutableStateOf("")
    var size by mutableStateOf("")
    var sizeType by mutableStateOf(0L)
    var notes by mutableStateOf("")
    var photo by mutableStateOf<ByteArray?>(null)

    private var loadedAnimalId: Long? = null

    fun loadAnimal(id: Long) {
        if (loadedAnimalId == id) return
        viewModelScope.launch(Dispatchers.IO) {
            val animal = animalsRepository.getAnimalById(id).first()
            animal?.let {
                withContext(Dispatchers.Main) {
                    name = it.name ?: ""
                    selectedObject = it.object_id
                    selectedSpecies = it.species_id
                    birthDate = it.birth_date ?: ""
                    gender = it.gender ?: ""
                    size = it.size?.toString() ?: ""
                    sizeType = it.size_type ?: 0
                    notes = it.notes ?: ""
                    photo = it.photo
                    loadedAnimalId = id
                }
            }
        }
    }

    // Function to clear the form after saving
    fun clearForm() {
        name = ""
        selectedObject = null
        selectedSpecies = null
        birthDate = ""
        gender = ""
        size = ""
        sizeType = 0L
        notes = ""
        photo = null
        loadedAnimalId = null
    }

    val availableObjects: StateFlow<List<Objects>> = objectsRepository.getAllObjects().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val availableSpecies: StateFlow<List<Species>> = speciesRepository.getAllSpecies().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun getAnimalById(id: Long) = animalsRepository.getAnimalById(id)

    fun insertAnimal(
        animalId: Long?,
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
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (animalId == null) {
                    animalsRepository.insertAnimal(
                        objectId = objectId,
                        speciesId = speciesId,
                        name = name,
                        gender = gender,
                        birthDate = birthDate,
                        lastFeeding = lastFeeding,
                        lastSpray = lastSpray,
                        lastMolt = lastMolt,
                        size = size,
                        sizeType = sizeType,
                        notes = notes,
                        photo = photo
                    )
                } else {
                    animalsRepository.updateAnimal(
                        animalId = animalId,
                        objectId = objectId,
                        speciesId = speciesId,
                        name = name,
                        gender = gender,
                        birthDate = birthDate,
                        lastFeeding = lastFeeding,
                        lastSpray = lastSpray,
                        lastMolt = lastMolt,
                        size = size,
                        sizeType = sizeType,
                        notes = notes,
                        photo = photo,
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteAnimal(animalId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                animalsRepository.deleteAnimal(animalId)
                withContext(Dispatchers.Main) {
                    clearForm()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun insertObject(
        name: String,
        description: String?,
        length: Long?,
        width: Long?,
        height: Long?,
        location: String?
    ) = withContext(Dispatchers.IO) {
        try {
            objectsRepository.insertObject(
                name = name,
                description = description,
                length = length,
                width = width,
                height = height,
                locationName = location
            )
            // Explicitly fetch latest to select it
            val lastId = objectsRepository.getAllObjects().first()
                .maxByOrNull { it.object_id }?.object_id
            withContext(Dispatchers.Main) {
                selectedObject = lastId
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun updateObject(
        objectId: Long,
        name: String,
        description: String?,
        length: Long?,
        width: Long?,
        height: Long?,
        location: String?
    ) = withContext(Dispatchers.IO) {
        try {
            objectsRepository.updateObject(
                objectId = objectId,
                name = name,
                description = description,
                length = length,
                width = width,
                height = height,
                locationName = location
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun deleteObject(objectId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                objectsRepository.deleteObject(objectId)
                if (selectedObject == objectId) {
                    withContext(Dispatchers.Main) {
                        selectedObject = null
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun insertSpecies(
        latinName: String,
        commonName: String?,
        description: String?,
        temperatureMin: Double?,
        temperatureMax: Double?,
        humidityMin: Double?,
        humidityMax: Double?,
        lightCycleH: Long?
    ) = withContext(Dispatchers.IO) {
        try {
            speciesRepository.insertSpecies(
                nameLatin = latinName,
                nameCommon = commonName,
                description = description,
                temperatureMin = temperatureMin,
                temperatureMax = temperatureMax,
                humidityMin = humidityMin,
                humidityMax = humidityMax,
                lightCycleH = lightCycleH
            )
            // Explicitly fetch latest to select it
            val lastId = speciesRepository.getAllSpecies().first()
                .maxByOrNull { it.species_id }?.species_id
            withContext(Dispatchers.Main) {
                selectedSpecies = lastId
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
