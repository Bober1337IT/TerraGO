package com.terrago.app.viewmodel.animalformviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terrago.app.database.repositories.AnimalsRepository
import com.terrago.app.database.repositories.ObjectsRepository
import com.terrago.app.database.repositories.SpeciesRepository
import com.terrago.app.domain.DeleteAnimalUseCase
import com.terrago.app.domain.DeleteObjectUseCase
import com.terrago.app.domain.UpsertAnimalUseCase
import com.terrago.app.domain.UpsertObjectUseCase
import com.terrago.app.domain.UpsertSpeciesUserCase

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
