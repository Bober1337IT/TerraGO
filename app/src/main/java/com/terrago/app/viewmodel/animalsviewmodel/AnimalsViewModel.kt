package com.terrago.app.viewmodel.animalsviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terrago.app.database.entity.AnimalDetails
import com.terrago.app.database.entity.AnimalPreview
import com.terrago.app.database.repositories.AnimalsRepository
import com.terrago.app.domain.UpdateAnimalFieldUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AnimalsViewModel(
    private val animalsRepository: AnimalsRepository,
    private val updateAnimalFieldUseCase: UpdateAnimalFieldUseCase
) : ViewModel() {

    val animalsPreview: StateFlow<List<AnimalPreview>> =
        animalsRepository.getAnimalsPreview()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun getAnimalDetails(id: Long): Flow<AnimalDetails?> {
        return animalsRepository.getAnimalsDetailsById(id)
    }

    fun setLastFeeding(id: Long) {
        viewModelScope.launch {
            updateAnimalFieldUseCase.setLastFeeding(id)
        }
    }

    fun setLastSpray(id: Long) {
        viewModelScope.launch {
            updateAnimalFieldUseCase.setLastSpray(id)
        }
    }

    fun setLastMolt(id: Long) {
        viewModelScope.launch {
            updateAnimalFieldUseCase.setLastMolt(id)
        }
    }

    fun setSize(id: Long, size: Long) {
        viewModelScope.launch {
            updateAnimalFieldUseCase.setSize(id, size)
        }
    }
}
