package com.terrago.app.presentation.viewmodel.animalsviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terrago.app.data.repositories.AnimalsRepository
import com.terrago.app.domain.usecase.UpdateAnimalFieldUseCase

class AnimalsViewModelFactory(
    private val repo: AnimalsRepository,
    private val updateAnimalFieldUseCase: UpdateAnimalFieldUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimalsViewModel(repo, updateAnimalFieldUseCase) as T
    }
}