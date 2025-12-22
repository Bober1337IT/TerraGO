package com.terrago.app.viewmodel.animalsviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terrago.app.database.entity.AnimalDetails
import com.terrago.app.database.entity.AnimalPreview
import com.terrago.app.database.repositories.AnimalsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

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
}