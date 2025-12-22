package com.terrago.app.viewmodel.animalform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terrago.app.database.repositories.AnimalsRepository
import com.terrago.app.database.repositories.ObjectsRepository
import com.terrago.app.database.repositories.SpeciesRepository
import com.terrago.app.viewmodel.animalformviewmodel.AnimalFormViewModel

class AnimalFormViewModelFactory(
    private val animalsRepo: AnimalsRepository,
    private val objectsRepo: ObjectsRepository,
    private val speciesRepo: SpeciesRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimalFormViewModel(animalsRepo, objectsRepo, speciesRepo) as T
    }
}
