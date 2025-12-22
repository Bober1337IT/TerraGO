package com.terrago.app.ui.screens.animalform

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.terrago.app.viewmodel.animalformviewmodel.AnimalFormViewModel
import com.terrago.app.navigation.graph.routes.AnimalFormRoutes
import kotlinx.coroutines.flow.first

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

    // Form State
    var name by remember { mutableStateOf("") }
    var selectedObject by remember { mutableStateOf<Long?>(null) }
    var selectedSpecies by remember { mutableStateOf<Long?>(null) }
    var birthDate by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var size by remember { mutableStateOf("") }
    var sizeType by remember { mutableStateOf<Long>(0) } // 0: cm, 1: molt, 2: other
    var notes by remember { mutableStateOf("") }

    // Dropdown Expanded States
    var objExp by remember { mutableStateOf(false) }
    var specExp by remember { mutableStateOf(false) }
    var genderExp by remember { mutableStateOf(false) }
    var sizeTypeExp by remember { mutableStateOf(false) }

    LaunchedEffect(animalId) {
        if (animalId != null) {
            val animal = viewModel.getAnimalById(animalId).first()
            animal?.let {
                name = it.name ?: ""
                selectedObject = it.object_id
                selectedSpecies = it.species_id
                birthDate = it.birth_date ?: ""
                gender = it.gender ?: ""
                size = it.size?.toString() ?: ""
                sizeType = it.size_type ?: 0
                notes = it.notes ?: ""
            }
        }
    }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        // Name
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        // Species dropdown
        ExposedDropdownMenuBox(expanded = specExp, onExpandedChange = { specExp = !specExp }) {
            TextField(
                value = species.find { it.species_id == selectedSpecies }?.name_latin
                    ?: "Select Species",
                onValueChange = {},
                readOnly = true,
                label = { Text("Species") },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(expanded = specExp, onDismissRequest = { specExp = false }) {
                species.forEach { spec ->
                    DropdownMenuItem(
                        text = { Text(spec.name_latin) },
                        onClick = {
                            selectedSpecies = spec.species_id
                            specExp = false
                        }
                    )
                }
                DropdownMenuItem(
                    text = { Text("+ New Species") },
                    onClick = {
                        specExp = false
                        navController.navigate(AnimalFormRoutes.NEW_SPECIES)
                    }
                )
            }
        }

        // Object Dropdown
        ExposedDropdownMenuBox(expanded = objExp, onExpandedChange = { objExp = !objExp }) {
            TextField(
                value = objects.find { it.object_id == selectedObject }?.name ?: "Select Habitat",
                onValueChange = {},
                readOnly = true,
                label = { Text("Habitat") },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(expanded = objExp, onDismissRequest = { objExp = false }) {
                objects.forEach { obj ->
                    DropdownMenuItem(
                        text = { Text(obj.name) },
                        onClick = { selectedObject = obj.object_id; objExp = false })
                }
                DropdownMenuItem(
                    text = { Text("+ New Habitat") },
                    onClick = { navController.navigate(AnimalFormRoutes.NEW_HABITAT) })
            }
        }

        // Birthdate
        TextField(
            value = birthDate,
            onValueChange = { birthDate = it },
            label = { Text("Birthdate") },
            modifier = Modifier.fillMaxWidth()
        )

        // Gender Dropdown
        ExposedDropdownMenuBox(
            expanded = genderExp,
            onExpandedChange = { genderExp = !genderExp }) {
            TextField(
                value = gender.ifBlank { "Select Gender" },
                onValueChange = {},
                readOnly = true,
                label = { Text("Gender") },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(expanded = genderExp, onDismissRequest = { genderExp = false }) {
                listOf("Male", "Female", "Not Sexed").forEach { g ->
                    DropdownMenuItem(
                        text = { Text(g) },
                        onClick = { gender = g; genderExp = false })
                }
            }
        }

        // Size and Size type
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = size,
                onValueChange = { size = it },
                label = { Text("Size") },
                modifier = Modifier.weight(1f)
            )
            ExposedDropdownMenuBox(
                expanded = sizeTypeExp,
                onExpandedChange = { sizeTypeExp = !sizeTypeExp },
                modifier = Modifier.weight(1f)
            ) {
                TextField(
                    value = when (sizeType) {
                        0L -> "cm"; 1L -> "molt"; else -> "other"
                    },
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Unit") },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = sizeTypeExp,
                    onDismissRequest = { sizeTypeExp = false }) {
                    DropdownMenuItem(
                        text = { Text("cm") },
                        onClick = { sizeType = 0; sizeTypeExp = false })
                    DropdownMenuItem(
                        text = { Text("molt") },
                        onClick = { sizeType = 1; sizeTypeExp = false })
                }
            }
        }

        // Notes
        TextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notes") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (selectedObject != null && selectedSpecies != null) {
                    viewModel.insertAnimal(
                        objectId = selectedObject!!,
                        speciesId = selectedSpecies!!,
                        name = name,
                        gender = gender,
                        birthDate = birthDate,
                        lastFeeding = null,
                        lastSpray = null,
                        lastMolt = null,
                        size = size.toLongOrNull(),
                        sizeType = sizeType,
                        notes = notes,
                        photo = null
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
