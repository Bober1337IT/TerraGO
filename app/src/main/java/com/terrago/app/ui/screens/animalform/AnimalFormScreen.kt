package com.terrago.app.ui.screens.animalform

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terrago.app.viewmodel.animalformviewmodel.AnimalFormViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.navigation.NavController
import com.terrago.app.navigation.graph.routes.AnimalFormRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalFormScreen(
    animalId: Long?,
    viewModel: AnimalFormViewModel,
    navController: NavController,
    onBack: () -> Unit
) {
    val objects by viewModel.availableObjects.collectAsState()
    val species by viewModel.availableSpecies.collectAsState()

    // Main Form State
    var name by remember { mutableStateOf("") }
    var selectedObject by remember { mutableStateOf<Long?>(null) }
    var selectedSpecies by remember { mutableStateOf<Long?>(null) }
    var notes by remember { mutableStateOf("") }

    // Fetch Animal Details if editing
    LaunchedEffect(animalId) {
        if (animalId != null) {
            viewModel.getAnimalById(animalId).collect { animal ->
                animal?.let {
                    name = it.name ?: ""
                    selectedObject = it.object_id
                    selectedSpecies = it.species_id
                    notes = it.notes ?: ""
                }
            }
        }
    }

    // Dropdown States
    var objectExpanded by remember { mutableStateOf(false) }
    var speciesExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text(if (animalId == null) "Add Animal" else "Edit Animal") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Animal Name") },
                modifier = Modifier.fillMaxWidth()
            )

            // OBJECT SELECTOR
            ExposedDropdownMenuBox(
                expanded = objectExpanded,
                onExpandedChange = { objectExpanded = !objectExpanded }
            ) {
                OutlinedTextField(
                    value = objects.find { it.object_id == selectedObject }?.name ?: "Select Habitat",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Habitat") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = objectExpanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = objectExpanded, onDismissRequest = { objectExpanded = false }) {
                    objects.forEach { obj ->
                        DropdownMenuItem(
                            text = { Text(obj.name) },
                            onClick = { selectedObject = obj.object_id; objectExpanded = false }
                        )
                    }
                    HorizontalDivider()
                    DropdownMenuItem(
                        text = { Text("Add New Habitat...", color = MaterialTheme.colorScheme.primary) },
                        leadingIcon = { Icon(Icons.Default.Add, contentDescription = null) },
                        onClick = {
                            objectExpanded = false
                            navController.navigate(AnimalFormRoutes.NEW_HABITAT)
                        }
                    )
                }
            }

            // SPECIES SELECTOR
            ExposedDropdownMenuBox(
                expanded = speciesExpanded,
                onExpandedChange = { speciesExpanded = !speciesExpanded }
            ) {
                OutlinedTextField(
                    value = species.find { it.species_id == selectedSpecies }?.name_common ?: "Select Species",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Species") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = speciesExpanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = speciesExpanded, onDismissRequest = { speciesExpanded = false }) {
                    species.forEach { spec ->
                        DropdownMenuItem(
                            text = { Text(spec.name_common) },
                            onClick = { selectedSpecies = spec.species_id; speciesExpanded = false }
                        )
                    }
                    HorizontalDivider()
                    DropdownMenuItem(
                        text = { Text("Add New Species...", color = MaterialTheme.colorScheme.primary) },
                        leadingIcon = { Icon(Icons.Default.Add, contentDescription = null) },
                        onClick = {
                            speciesExpanded = false
                            navController.navigate(AnimalFormRoutes.NEW_SPECIES)
                        }
                    )
                }
            }

            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes") },
                modifier = Modifier.fillMaxWidth().height(120.dp)
            )

            Button(
                onClick = {
                    if (selectedObject != null && selectedSpecies != null) {
                        viewModel.insertAnimal(
                            objectId = selectedObject!!,
                            speciesId = selectedSpecies!!,
                            name = name,
                            gender = null, birthDate = null, lastFeeding = null,
                            lastSpray = null, lastMolt = null, size = null,
                            sizeType = null, notes = notes, photo = null
                        )
                        onBack()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = selectedObject != null && selectedSpecies != null
            ) {
                Text("Save Animal")
            }
        }
    }
}