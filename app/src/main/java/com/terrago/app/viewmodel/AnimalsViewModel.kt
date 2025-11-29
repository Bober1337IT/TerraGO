package com.terrago.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terrago.app.database.entity.AnimalPreview
import com.terrago.app.database.repositories.AnimalsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class AnimalsViewModel(
    private val repository: AnimalsRepository
) : ViewModel() {

    val animalsPreview: StateFlow<List<AnimalPreview>> =
        repository.getAnimalsPreview()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
}