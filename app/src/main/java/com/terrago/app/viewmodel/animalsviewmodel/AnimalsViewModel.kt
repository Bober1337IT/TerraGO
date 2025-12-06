package com.terrago.app.viewmodel.animalsviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terrago.app.database.entity.AnimalDetails
import com.terrago.app.database.entity.AnimalPreview
import com.terrago.app.database.repositories.AnimalsRepository
import com.terrago.app.db.Animals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AnimalsViewModel(
    private val animalsRepository: AnimalsRepository
) : ViewModel() {

    val animalsPreview: StateFlow<List<AnimalPreview>> =
        animalsRepository.getAnimalsPreview()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Companion.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun getAnimalDetails(id: Long): Flow<AnimalDetails?> {
        return animalsRepository.getAnimalsDetailsById(id)
    }

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

    // For testing purposes
    val getAllAnimals: StateFlow<List<Animals>> =
        animalsRepository.getAllAnimals()
            .stateIn(
                viewModelScope,
                SharingStarted.Companion.WhileSubscribed(5000),
                emptyList()
            )

    // For testing purposes
    fun deleteAllAnimals() {
        animalsRepository.deleteAllAnimals()
    }
}