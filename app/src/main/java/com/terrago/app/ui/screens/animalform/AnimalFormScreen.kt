package com.terrago.app.ui.screens.animalform

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.terrago.app.viewmodel.animalformviewmodel.AnimalFormViewModel
import com.terrago.app.navigation.graph.routes.AnimalFormRoutes
import com.terrago.app.ui.components.PhotoFromByteArray
import com.terrago.app.ui.components.rememberPhotoPicker
import com.terrago.app.ui.components.topbar.TopActionsBar
import kotlinx.coroutines.flow.first

@RequiresApi(Build.VERSION_CODES.O)
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

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFEFFFEF))) {
        // Name
        TopActionsBar(onBackClick = onBack)
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

//        TextField(
//            value = viewModel.name,
//            onValueChange = { viewModel.name = it },
//            label = { Text("Name") },
//            modifier = Modifier.fillMaxWidth()
//        )

            // Species dropdown
            Label("Animal species:")
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ExposedDropdownMenuBox(
                    expanded = specExp,
                    onExpandedChange = { specExp = !specExp }) {
                    OutlinedTextField(
                        value = species.find { it.species_id == viewModel.selectedSpecies }?.name_latin
                            ?: "Choose animal species...",
                        onValueChange = {},
                        readOnly = true,
                        placeholder = { Text("Choose animal species...") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = specExp)
                        },
                        modifier = Modifier
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = specExp,
                        onDismissRequest = { specExp = false }) {
                        species.forEach { spec ->
                            DropdownMenuItem(
                                text = { Text(spec.name_latin) },
                                onClick = {
                                    viewModel.selectedSpecies = spec.species_id
                                    specExp = false
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        navController.navigate(AnimalFormRoutes.NEW_SPECIES)
                    },
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = Color(0xFF2E7D32),
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add species",
                        tint = Color.White
                    )
                }
            }
            Spacer(Modifier.height(12.dp))

            Label("Animal name (optional):")
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = viewModel.name,
                onValueChange = { viewModel.name = it },
                placeholder = { Text("Enter animal name...") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))

            Label("Birth date:")
            Spacer(Modifier.height(8.dp))
            val datePickerState = rememberDatePickerState()
            var showDatePicker by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = viewModel.birthDate,
                onValueChange = {viewModel.birthDate = it},
                readOnly = true,
                placeholder = { Text("Enter birth date (DD-MM-YYYY)...") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Pick date"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showDatePicker = true }
            )
            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        TextButton(onClick = {
                            datePickerState.selectedDateMillis?.let { millis ->
                                val date = java.time.Instant
                                    .ofEpochMilli(millis)
                                    .atZone(java.time.ZoneId.systemDefault())
                                    .toLocalDate()

                                viewModel.birthDate =
                                    "%02d-%02d-%04d".format(
                                        date.dayOfMonth,
                                        date.monthValue,
                                        date.year
                                    )
                            }
                            showDatePicker = false
                        }) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDatePicker = false }) {
                            Text("Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
            Spacer(Modifier.height(12.dp))
//            OutlinedTextField(
//                value = viewModel.birthDate,
//                onValueChange = {  },
//                placeholder = { Text("DD-MM-YYYY") },
//                modifier = Modifier.fillMaxWidth()
//            )

            Label("Pick gender:")
            Spacer(Modifier.height(8.dp))
            GenderRow(
                selected = viewModel.gender,
                onSelect = { viewModel.gender = it }
            )
            Spacer(Modifier.height(12.dp))

            Label("Animal size:")
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ExposedDropdownMenuBox(
                    expanded = sizeTypeExp,
                    onExpandedChange = { sizeTypeExp = !sizeTypeExp },
                    modifier = Modifier.width(90.dp)
                ) {
                    OutlinedTextField(
                        value = when (viewModel.sizeType) {
                            0L -> "cm"; 1L -> "L"; else -> "other"
                        },
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = sizeTypeExp,
                        onDismissRequest = { sizeTypeExp = false }
                    ) {
                        DropdownMenuItem(text = { Text("cm") }, onClick = {
                            viewModel.sizeType = 0
                            sizeTypeExp = false
                        })
                        DropdownMenuItem(text = { Text("L") }, onClick = {
                            viewModel.sizeType = 1
                            sizeTypeExp = false
                        })
                        DropdownMenuItem(text = { Text("Other") }, onClick = {
                            viewModel.sizeType = 2
                            sizeTypeExp = false
                        })
                    }
                }
                OutlinedTextField(
                    value = viewModel.size,
                    onValueChange = { viewModel.size = it },
                    placeholder = { Text("Enter animal size...") },
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(Modifier.height(12.dp))

            Label("Animal photo (optional):")
            Spacer(Modifier.height(8.dp))
            OutlinedButton(onClick = { photoPicker.launchGallery() }) {
                Text("Select image")
            }
            if (viewModel.photo != null) {
                PhotoFromByteArray(
                    bytes = viewModel.photo,
                    modifier = Modifier.size(120.dp)
                )
            }
            Spacer(Modifier.height(12.dp))

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
            Label("Additional notes (optional):")
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = viewModel.notes,
                onValueChange = { viewModel.notes = it },
                placeholder = { Text("Enter notes...") },
                modifier = Modifier
                    .fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(16.dp))

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
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2E7D32)
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                enabled = viewModel.selectedObject != null && viewModel.selectedSpecies != null
            ) {
                Text("ACCEPT")
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.Check, contentDescription = null)
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
}
@Composable
private fun Label(text: String) {
    Text(text, style = MaterialTheme.typography.bodyMedium)
}
@Composable
private fun GenderRow(
    selected: String,
    onSelect: (String) -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        listOf("Male", "Female", "Not Sexed").forEach {
            GenderChip(
                label = it,
                selected = selected == it,
                onClick = { onSelect(it) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}
@Composable
private fun GenderChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = Modifier
            .background(
                if (selected) Color.Black else Color.White,
                RoundedCornerShape(8.dp)
            )
            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(label, color = if (selected) Color.White else Color.Black)
    }
}
