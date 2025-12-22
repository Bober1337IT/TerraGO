package com.terrago.app.viewmodel.animalformviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terrago.app.database.repositories.AnimalsRepository
import com.terrago.app.database.repositories.ObjectsRepository
import com.terrago.app.database.repositories.SpeciesRepository
import com.terrago.app.db.Objects
import com.terrago.app.db.Species
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AnimalFormViewModel(
    private val animalsRepository: AnimalsRepository,
    private val objectsRepository: ObjectsRepository,
    private val speciesRepository: SpeciesRepository
) : ViewModel() {

    // Used to populate dropdown menus
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
        viewModelScope.launch {
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
        }
    }

    //TODO
    fun insertObject(name: String, location: String?) {
        viewModelScope.launch {
            objectsRepository.insertObject(name = name, locationName = location)
        }
    }

    //TODO
    fun insertSpecies(commonName: String, latinName: String?) {
        viewModelScope.launch {
            speciesRepository.insertSpecies(nameCommon = commonName, nameLatin = latinName)
        }
    }
}
