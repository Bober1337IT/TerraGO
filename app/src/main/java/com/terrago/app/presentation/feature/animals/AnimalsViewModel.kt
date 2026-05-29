package com.terrago.app.presentation.feature.animals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terrago.app.data.local.AppInitializer
import com.terrago.app.domain.animals.AnimalsRepository
import com.terrago.app.domain.animals.model.AnimalDetails
import com.terrago.app.domain.animals.model.AnimalPreview
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
    private val appInitializer: AppInitializer
) : ViewModel() {

    init {
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

    fun getAnimalDetails(id: Long): Flow<AnimalDetails?> =
        animalsRepository.getAnimalsDetailsById(id)

    fun setLastFeeding(id: Long) = viewModelScope.launch {
        animalsRepository.setLastFeeding(id)
    }

    fun setLastSpray(id: Long) = viewModelScope.launch {
        animalsRepository.setLastSpray(id)
    }

    fun setLastMolt(id: Long) = viewModelScope.launch {
        animalsRepository.setLastMolt(id)
    }

    fun setSize(id: Long, size: Long) = viewModelScope.launch {
        animalsRepository.setSize(id, size)
    }
}
