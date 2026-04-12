package com.terrago.app.presentation.feature.animals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terrago.app.data.local.AppInitializer
import com.terrago.app.data.local.entity.AnimalDetails
import com.terrago.app.data.local.entity.AnimalPreview
import com.terrago.app.domain.animals.AnimalsRepository
import com.terrago.app.domain.animals.usecase.UpdateAnimalFieldUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalsViewModel @Inject constructor(
    private val animalsRepository: AnimalsRepository,
    private val updateAnimalFieldUseCase: UpdateAnimalFieldUseCase,
    private val appInitializer: AppInitializer
) : ViewModel() {

    init {
        // Call the initializer once when the app starts
        viewModelScope.launch {
            appInitializer.initialize()
        }
    }

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
