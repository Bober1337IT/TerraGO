package com.terrago.app.presentation.feature.animalform

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terrago.app.domain.animals.AnimalsRepository
import com.terrago.app.domain.animals.model.Animal
import com.terrago.app.domain.animals.usecase.UpsertAnimalUseCase
import com.terrago.app.domain.objects.ObjectsRepository
import com.terrago.app.domain.objects.model.TerraObject
import com.terrago.app.domain.objects.usecase.UpsertObjectUseCase
import com.terrago.app.domain.species.SpeciesRepository
import com.terrago.app.domain.species.model.Species
import com.terrago.app.domain.species.usecase.UpsertSpeciesUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalFormViewModel @Inject constructor(
    private val animalsRepository: AnimalsRepository,
    private val objectsRepository: ObjectsRepository,
    private val speciesRepository: SpeciesRepository,
    private val upsertAnimalUseCase: UpsertAnimalUseCase,
    private val upsertObjectUseCase: UpsertObjectUseCase,
    private val upsertSpeciesUseCase: UpsertSpeciesUserCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AnimalFormUiState())
    val uiState: StateFlow<AnimalFormUiState> = _uiState.asStateFlow()

    fun updateState(transform: (AnimalFormUiState) -> AnimalFormUiState) {
        _uiState.update(transform)
    }

    val availableObjects: StateFlow<List<TerraObject>> = objectsRepository.getAllObjects().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList()
    )

    val availableSpecies: StateFlow<List<Species>> = speciesRepository.getAllSpecies().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList()
    )

    @OptIn(FlowPreview::class)
    val filteredSpecies: StateFlow<List<Species>> = combine(
        availableSpecies,
        _uiState.map { it.speciesSearchQuery }.distinctUntilChanged()
            .debounce { query -> if (query.isEmpty()) 0L else 400L }
    ) { allSpecies, query ->
        val sorted = allSpecies.sortedBy { it.nameLatin }
        if (query.isEmpty()) {
            sorted
        } else {
            sorted.filter {
                it.nameLatin.contains(query, ignoreCase = true) ||
                    (it.nameCommon?.contains(query, ignoreCase = true) ?: false)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList()
    )

    fun onSpeciesSearchChange(query: String) {
        updateState { it.copy(speciesSearchQuery = query) }
    }

    var lastFeeding by mutableStateOf<String?>(null)
    var lastSpray by mutableStateOf<String?>(null)
    var lastMolt by mutableStateOf<String?>(null)

    private var loadedAnimalId: Long? = null

    fun loadAnimal(id: Long) {
        if (loadedAnimalId == id) return

        viewModelScope.launch {
            val animal = animalsRepository.getAnimalById(id).first() ?: return@launch
            val speciesName = availableSpecies.value.find { it.id == animal.speciesId }?.nameLatin
                ?: speciesRepository.getSpeciesById(animal.speciesId).first()?.nameLatin
                ?: ""

            _uiState.update { state ->
                state.copy(
                    name = animal.name ?: "",
                    selectedObject = animal.objectId,
                    selectedSpecies = animal.speciesId,
                    speciesSearchQuery = speciesName,
                    birthDate = animal.birthDate ?: "",
                    gender = animal.gender ?: "",
                    size = animal.size?.toString() ?: "",
                    sizeType = animal.sizeType ?: 0L,
                    notes = animal.notes ?: "",
                    photo = animal.photo
                )
            }

            lastFeeding = animal.lastFeeding
            lastSpray = animal.lastSpray
            lastMolt = animal.lastMolt
            loadedAnimalId = id
        }
    }

    fun clearForm() {
        _uiState.update { AnimalFormUiState() }
        lastFeeding = null
        lastSpray = null
        lastMolt = null
        loadedAnimalId = null
    }

    fun clearSpeciesFields() {
        _uiState.update { state ->
            state.copy(
                speciesLatinName = "",
                speciesCommonName = "",
                speciesDescription = "",
                speciesTempMin = "",
                speciesTempMax = "",
                speciesHumMin = "",
                speciesHumMax = "",
                speciesLightCycle = ""
            )
        }
    }

    fun clearObjectFields() {
        _uiState.update { state ->
            state.copy(
                objectName = "",
                objectLocationName = "",
                objectDescription = "",
                objectLength = "",
                objectWidth = "",
                objectHeight = ""
            )
        }
    }

    fun upsertAnimal(animal: Animal) {
        viewModelScope.launch {
            upsertAnimalUseCase(animal)
        }
    }

    fun deleteAnimal(animalId: Long) {
        viewModelScope.launch {
            animalsRepository.deleteAnimal(animalId)
            clearForm()
        }
    }

    fun upsertObject(obj: TerraObject) {
        viewModelScope.launch {
            val resultId = upsertObjectUseCase(obj)
            _uiState.update { it.copy(selectedObject = resultId) }
            clearObjectFields()
        }
    }

    fun deleteObject(objectId: Long) {
        viewModelScope.launch {
            objectsRepository.deleteObject(objectId)
            if (_uiState.value.selectedObject == objectId) {
                _uiState.update { it.copy(selectedObject = null) }
            }
        }
    }

    fun insertSpecies(species: Species) {
        viewModelScope.launch {
            val newId = upsertSpeciesUseCase(species)
            _uiState.update {
                it.copy(
                    selectedSpecies = newId,
                    speciesSearchQuery = species.nameLatin
                )
            }
            clearSpeciesFields()
        }
    }
}
