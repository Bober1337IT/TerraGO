package com.terrago.app.presentation.viewmodel.animalformviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terrago.app.data.repositories.AnimalsRepository
import com.terrago.app.data.repositories.ObjectsRepository
import com.terrago.app.data.repositories.SpeciesRepository
import com.terrago.app.domain.usecase.DeleteAnimalUseCase
import com.terrago.app.domain.usecase.DeleteObjectUseCase
import com.terrago.app.domain.usecase.UpsertAnimalUseCase
import com.terrago.app.domain.usecase.UpsertObjectUseCase
import com.terrago.app.domain.usecase.UpsertSpeciesUserCase

class AnimalFormViewModelFactory(
    private val animalsRepo: AnimalsRepository,
    private val objectsRepo: ObjectsRepository,
    private val speciesRepo: SpeciesRepository,
    private val upsertAnimalUseCase: UpsertAnimalUseCase,
    private val deleteAnimalUseCase: DeleteAnimalUseCase,
    private val upsertObjectUseCase: UpsertObjectUseCase,
    private val deleteObjectUseCase: DeleteObjectUseCase,
    private val upsertSpeciesUseCase: UpsertSpeciesUserCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimalFormViewModel(
            animalsRepo,
            objectsRepo,
            speciesRepo,
            upsertAnimalUseCase,
            deleteAnimalUseCase,
            upsertObjectUseCase,
            deleteObjectUseCase,
            upsertSpeciesUseCase
        ) as T
    }
}
