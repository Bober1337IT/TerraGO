package com.terrago.app.viewmodel.animalformviewmodel

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
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AnimalFormViewModel(
    private val animalsRepository: AnimalsRepository,
    private val objectsRepository: ObjectsRepository,
    private val speciesRepository: SpeciesRepository
) : ViewModel() {

    val availableObjects: StateFlow<List<Objects>> = objectsRepository.getAllObjects()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val availableSpecies: StateFlow<List<Species>> = speciesRepository.getAllSpecies()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun getAnimalById(id: Long) = animalsRepository.getAnimalById(id)

    fun insertAnimal(
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
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun insertObject(
        name: String,
        description: String?,
        length: Long?,
        width: Long?,
        height: Long?,
        location: String?
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                objectsRepository.insertObject(
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
    }

    fun insertSpecies(
        latinName: String,
        commonName: String?,
        description: String?,
        temperatureMin: Double?,
        temperatureMax: Double?,
        humidityMin: Double?,
        humidityMax: Double?,
        lightCycleH: Long?
    ) {
        viewModelScope.launch(Dispatchers.IO) {
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
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

