package com.terrago.app.ui.screens.animalform

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.terrago.app.viewmodel.animalformviewmodel.AnimalFormViewModel
import com.terrago.app.navigation.graph.routes.AnimalFormRoutes
import com.terrago.app.ui.components.PhotoFromByteArray
import com.terrago.app.ui.components.rememberPhotoPicker
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

    // Dropdown Expanded States
    var objExp by remember { mutableStateOf(false) }
    var specExp by remember { mutableStateOf(false) }
    var genderExp by remember { mutableStateOf(false) }
    var sizeTypeExp by remember { mutableStateOf(false) }

    val photoPicker = rememberPhotoPicker { bytes ->
        viewModel.photo = bytes
    }

    LaunchedEffect(animalId) {
        if (animalId != null && viewModel.name.isEmpty()) {
            val animal = viewModel.getAnimalById(animalId).first()
            animal?.let {
                viewModel.name = it.name ?: ""
                viewModel.selectedObject = it.object_id
                viewModel.selectedSpecies = it.species_id
                viewModel.birthDate = it.birth_date ?: ""
                viewModel.gender = it.gender ?: ""
                viewModel.size = it.size?.toString() ?: ""
                viewModel.sizeType = it.size_type ?: 0
                viewModel.notes = it.notes ?: ""
                viewModel.photo = it.photo ?: byteArrayOf()
            }
        }
    }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        // Name
        TextField(
            value = viewModel.name,
            onValueChange = { viewModel.name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        // Species dropdown
        ExposedDropdownMenuBox(expanded = specExp, onExpandedChange = { specExp = !specExp }) {
            TextField(
                value = species.find { it.species_id == viewModel.selectedSpecies }?.name_latin
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
                            viewModel.selectedSpecies = spec.species_id
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
                value = objects.find { it.object_id == viewModel.selectedObject }?.name ?: "Select Habitat",
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
                        onClick = { viewModel.selectedObject = obj.object_id; objExp = false })
                }
                DropdownMenuItem(
                    text = { Text("+ New Habitat") },
                    onClick = { navController.navigate(AnimalFormRoutes.NEW_HABITAT) })
            }
        }

        // Birthdate
        TextField(
            value = viewModel.birthDate,
            onValueChange = { viewModel.birthDate = it },
            label = { Text("Birthdate") },
            modifier = Modifier.fillMaxWidth()
        )

        // Gender Dropdown
        ExposedDropdownMenuBox(
            expanded = genderExp,
            onExpandedChange = { genderExp = !genderExp }) {
            TextField(
                value = viewModel.gender.ifBlank { "Select Gender" },
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
                        onClick = { viewModel.gender = g; genderExp = false })
                }
            }
        }

        // Size and Size type
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = viewModel.size,
                onValueChange = { viewModel.size = it },
                label = { Text("Size") },
                modifier = Modifier.weight(1f)
            )
            ExposedDropdownMenuBox(
                expanded = sizeTypeExp,
                onExpandedChange = { sizeTypeExp = !sizeTypeExp },
                modifier = Modifier.weight(1f)
            ) {
                TextField(
                    value = when (viewModel.sizeType) {
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
                        onClick = { viewModel.sizeType = 0; sizeTypeExp = false })
                    DropdownMenuItem(
                        text = { Text("molt") },
                        onClick = { viewModel.sizeType = 1; sizeTypeExp = false })
                }
            }
        }

        // PHOTO SECTION
        Text(text = "Animal Photo")
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { photoPicker.launchGallery() },
                modifier = Modifier
                    .weight(1f)
                    .padding(2.dp)
            ) {
                Text("Gallery")
            }
            Button(
                onClick = { photoPicker.launchCamera() },
                modifier = Modifier
                    .weight(1f)
                    .padding(2.dp)
            ) {
                Text("Camera")
            }
        }

        // Preview and Remove option
        if (viewModel.photo != null) {
            PhotoFromByteArray(
                bytes = viewModel.photo,
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp)
            )
            TextButton(onClick = { viewModel.photo = null }) {
                Text("Remove Photo", color = MaterialTheme.colorScheme.error)
            }
        }


        // Notes
        TextField(
            value = viewModel.notes,
            onValueChange = { viewModel.notes = it },
            label = { Text("Notes") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (viewModel.selectedObject != null && viewModel.selectedSpecies != null) {
                    viewModel.insertAnimal(
                        animalId = animalId,
                        objectId = viewModel.selectedObject!!,
                        speciesId = viewModel.selectedSpecies!!,
                        name = viewModel.name,
                        gender = viewModel.gender,
                        birthDate = viewModel.birthDate,
                        lastFeeding = null,
                        lastSpray = null,
                        lastMolt = null,
                        size = viewModel.size.toLongOrNull(),
                        sizeType = viewModel.sizeType,
                        notes = viewModel.notes,
                        photo = viewModel.photo
                    )
                    viewModel.clearForm()
                    onBack()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = viewModel.selectedObject != null && viewModel.selectedSpecies != null
        ) {
            Text("Save Animal")
        }
        if (animalId != null) {
            Button(
                onClick = {
                    viewModel.deleteAnimal(animalId)
                    onBack()
                    onBack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Delete Animal")
            }
        }
        Spacer(modifier = Modifier.height(64.dp))
    }
}
