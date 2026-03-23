package com.terrago.app.viewmodel.animalsviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terrago.app.database.repositories.AnimalsRepository
import com.terrago.app.domain.UpdateAnimalFieldUseCase

class AnimalsViewModelFactory(
    private val repo: AnimalsRepository,
    private val updateAnimalFieldUseCase: UpdateAnimalFieldUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimalsViewModel(repo, updateAnimalFieldUseCase) as T
    }
}