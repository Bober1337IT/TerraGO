package com.terrago.app.viewmodel.animalformviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terrago.app.database.repositories.AnimalsRepository
import com.terrago.app.database.repositories.ObjectsRepository
import com.terrago.app.database.repositories.SpeciesRepository
import com.terrago.app.db.Objects
import com.terrago.app.db.Species
import com.terrago.app.domain.DeleteAnimalUseCase
import com.terrago.app.domain.UpsertAnimalUseCase
import kotlinx.coroutines.Dispatchers
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
import kotlinx.coroutines.withContext

class AnimalFormViewModel(
    private val animalsRepository: AnimalsRepository,
    private val objectsRepository: ObjectsRepository,
    private val speciesRepository: SpeciesRepository,
    private val upsertAnimalUseCase: UpsertAnimalUseCase,
    private val deleteAnimalUseCase: DeleteAnimalUseCase
) : ViewModel() {

    // Internal mutable source that holds the current state of the entire form
    private val _uiState = MutableStateFlow(AnimalFormUiState())
    // Public read-only stream that the UI observes to refresh its components
    val uiState: StateFlow<AnimalFormUiState> = _uiState.asStateFlow()

    // A function that allows the UI to modify any part of the state
    // It takes the current state, applies your changes via .copy() and pushes the new version to the UI
    fun updateState(transform: (AnimalFormUiState) -> AnimalFormUiState) {
        _uiState.update(transform)
    }

    // Data Sources - Load eagerly so they are ready before the screen finishes transitioning
    val availableObjects: StateFlow<List<Objects>> = objectsRepository.getAllObjects().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly, // Start loading immediately in background
        initialValue = emptyList()
    )

    val availableSpecies: StateFlow<List<Species>> = speciesRepository.getAllSpecies().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly, // Start loading immediately in background
        initialValue = emptyList()
    )

    // Combines the full list from DB with the search query from UI state with a debounce delay
    @OptIn(FlowPreview::class)
    val filteredSpecies: StateFlow<List<Species>> = combine(
        availableSpecies,
        _uiState.map { it.speciesSearchQuery }.distinctUntilChanged()
            // Use 0ms delay for empty query (initial state) so list is ready instantly, 
            // but keep 400ms for active typing.
            .debounce { query -> if (query.isEmpty()) 0L else 400L }
    ) { allSpecies, query ->
        val sorted = allSpecies.sortedBy { it.name_latin }

        if (query.isEmpty()) {
            sorted
        } else {
            // Check if the query exactly matches the currently selected species name
            sorted.filter {
                it.name_latin.contains(query, ignoreCase = true) || (it.name_common?.contains(query, ignoreCase = true) ?: false)
            }
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly, // Start loading immediately in background
            initialValue = emptyList()
        )

    fun onSpeciesSearchChange(query: String) {
        updateState { it.copy(speciesSearchQuery = query) }
    }

    // Hidden form state to preserve care dates during edit
    var lastFeeding by mutableStateOf<String?>(null)
    var lastSpray by mutableStateOf<String?>(null)
    var lastMolt by mutableStateOf<String?>(null)

    private var loadedAnimalId: Long? = null

    fun loadAnimal(id: Long) {
        if (loadedAnimalId == id) return

        viewModelScope.launch(Dispatchers.IO) {
            val animal = animalsRepository.getAnimalById(id).first()

            animal?.let {
                // Find species name from pre-loaded list or fallback to DB
                val speciesName = availableSpecies.value.find { s -> s.species_id == it.species_id }?.name_latin ?: speciesRepository.getSpeciesById(it.species_id).first()?.name_latin ?: ""

                _uiState.update { state ->
                    state.copy(
                        name = it.name ?: "",
                        selectedObject = it.object_id,
                        selectedSpecies = it.species_id,
                        speciesSearchQuery = speciesName,
                        birthDate = it.birth_date ?: "",
                        gender = it.gender ?: "",
                        size = it.size?.toString() ?: "",
                        sizeType = it.size_type ?: 0L,
                        notes = it.notes ?: "",
                        photo = it.photo
                    )
                }

                // Populate hidden fields
                withContext(Dispatchers.Main) {
                    lastFeeding = it.last_feeding
                    lastSpray = it.last_spray
                    lastMolt = it.last_molt
                }

                loadedAnimalId = id
            }
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

    fun getAnimalById(id: Long) = animalsRepository.getAnimalById(id)

    fun insertAnimal(
        animalId: Long?,
        objectId: Long,
        speciesId: Long,
        name: String?,
        gender: String?,
        birthDate: String?,
        lastFeeding: String?,
        lastSpray: String?,
        lastMolt: String?,
        size: Long?,
        sizeType: Long?,
        notes: String?,
        photo: ByteArray?
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                upsertAnimalUseCase(
                    animalId = animalId,
                    objectId = objectId,
                    speciesId = speciesId,
                    name = name,
                    gender = gender,
                    birthDate = birthDate,
                    lastFeeding = lastFeeding,
                    lastSpray = lastSpray,
                    lastMolt = lastMolt,
                    size = size,
                    sizeType = sizeType,
                    notes = notes,
                    photo = photo
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteAnimal(animalId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                deleteAnimalUseCase(animalId)
                withContext(Dispatchers.Main) {
                    clearForm()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun insertObject(
        name: String,
        description: String?,
        length: Long?,
        width: Long?,
        height: Long?,
        location: String?
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                objectsRepository.insertObject(
                    name = name,
                    description = description,
                    length = length,
                    width = width,
                    height = height,
                    locationName = location
                )
                // Explicitly fetch latest to select it
                val lastId = objectsRepository.getAllObjects().first()
                    .maxByOrNull { it.object_id }?.object_id
                withContext(Dispatchers.Main) {
                    _uiState.update { it.copy(selectedObject = lastId) }
                    clearObjectFields()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateObject(
        objectId: Long,
        name: String,
        description: String?,
        length: Long?,
        width: Long?,
        height: Long?,
        location: String?
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                objectsRepository.updateObject(
                    objectId = objectId,
                    name = name,
                    description = description,
                    length = length,
                    width = width,
                    height = height,
                    locationName = location
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteObject(objectId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                objectsRepository.deleteObject(objectId)
                if (_uiState.value.selectedObject == objectId) {
                    withContext(Dispatchers.Main) {
                        _uiState.update { it.copy(selectedObject = null) }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun insertSpecies(
        latinName: String,
        commonName: String?,
        description: String?,
        temperatureMin: Double?,
        temperatureMax: Double?,
        humidityMin: Double?,
        humidityMax: Double?,
        lightCycleH: Long?
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                speciesRepository.insertSpecies(
                    nameLatin = latinName,
                    nameCommon = commonName,
                    description = description,
                    temperatureMin = temperatureMin,
                    temperatureMax = temperatureMax,
                    humidityMin = humidityMin,
                    humidityMax = humidityMax,
                    lightCycleH = lightCycleH
                )
                // Explicitly fetch latest to select it
                val lastId = speciesRepository.getAllSpecies().first()
                    .maxByOrNull { it.species_id }?.species_id
                withContext(Dispatchers.Main) {
                    _uiState.update { it.copy(selectedSpecies = lastId, speciesSearchQuery = latinName) }
                    clearSpeciesFields()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
