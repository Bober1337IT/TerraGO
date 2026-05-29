package com.terrago.app.presentation.feature.animalform

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.terrago.app.R
import com.terrago.app.domain.animals.model.Animal
import com.terrago.app.presentation.navigation.Screen.AnimalFormRoutes
import com.terrago.app.presentation.shared.components.photo.PhotoFromByteArray
import com.terrago.app.presentation.shared.components.photo.rememberPhotoPicker
import com.terrago.app.presentation.feature.animalform.components.DeleteConfirmationDialog
import com.terrago.app.presentation.feature.animalform.components.GenderButton
import com.terrago.app.presentation.feature.animalform.components.Label
import com.terrago.app.presentation.feature.animalform.AnimalFormViewModel
import com.terrago.app.presentation.shared.theme.TerraGOTheme

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalFormScreen(
    animalId: Long?,
    viewModel: AnimalFormViewModel = hiltViewModel(),
    navController: NavController,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val objects by viewModel.availableObjects.collectAsStateWithLifecycle()
    // Collect the filtered flow from ViewModel
    val filteredSpecies by viewModel.filteredSpecies.collectAsStateWithLifecycle()

    var speciesExpanded by remember { mutableStateOf(false) }
    var sizeTypeExp by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    val photoPicker =
        rememberPhotoPicker { bytes ->
            viewModel.updateState { it.copy(photo = bytes) }
        }

    BackHandler {
        viewModel.clearForm()
        onBack()
    }

    LaunchedEffect(animalId) {
        if (animalId != null) {
            viewModel.loadAnimal(animalId)
        }
    }

    TerraGOTheme(dynamicColor = false) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                    Icon(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier.height(40.dp),
                        tint = Color.Unspecified
                    )
                }, navigationIcon = {
                    IconButton(onClick = {
                        viewModel.clearForm()
                        onBack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier
                                .size(36.dp)
                                .border(2.dp, MaterialTheme.colorScheme.onSurface, CircleShape)
                                .padding(4.dp)
                        )
                    }
                }, actions = {
                    if (animalId != null) {
                        Button(
                            onClick = { showDeleteDialog = true },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                            shape = RoundedCornerShape(24.dp),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp)
                        ) {
                            Text(
                                "DELETE",
                                color = MaterialTheme.colorScheme.onError,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                            Spacer(Modifier.width(4.dp))
                            Icon(
                                Icons.Default.Close,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
                )
            }, containerColor = MaterialTheme.colorScheme.background
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Species Search Bar
                Label("Animal species:")
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ExposedDropdownMenuBox(
                        expanded = speciesExpanded,
                        onExpandedChange = { speciesExpanded = it },
                        modifier = Modifier.weight(1f)
                    ) {
                        OutlinedTextField(
                            value = uiState.speciesSearchQuery,
                            onValueChange = {
                                viewModel.onSpeciesSearchChange(it)
                                speciesExpanded = true
                            },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            placeholder = { Text("Search species...") },
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline
                            ),
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = speciesExpanded) })

                        if (filteredSpecies.isNotEmpty()) {
                            ExposedDropdownMenu(
                                expanded = speciesExpanded,
                                onDismissRequest = { speciesExpanded = false }) {
                                filteredSpecies.forEach { species ->
                                    DropdownMenuItem(text = {
                                        Column {
                                            Text(species.nameLatin, fontWeight = FontWeight.Bold)
                                            if (!species.nameCommon.isNullOrBlank()) {
                                                Text(
                                                    species.nameCommon,
                                                    style = MaterialTheme.typography.bodySmall
                                                )
                                            }
                                        }
                                    }, onClick = {
                                        viewModel.updateState {
                                            it.copy(
                                                selectedSpecies = species.id,
                                                speciesSearchQuery = species.nameLatin
                                            )
                                        }
                                        speciesExpanded = false
                                    })
                                }
                            }
                        }
                    }
                    Spacer(Modifier.width(8.dp))
                    IconButton(
                        onClick = { navController.navigate(AnimalFormRoutes.NEW_SPECIES) },
                        modifier = Modifier
                            .size(48.dp)
                            .background(MaterialTheme.colorScheme.primary, CircleShape)
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Add Species",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

                // Habitat Button
                Label("Choose Habitat:")
                Button(
                    onClick = { navController.navigate(AnimalFormRoutes.NEW_HABITAT) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp, MaterialTheme.colorScheme.outline
                    )
                ) {
                    Text(
                        text = objects.find { it.id == uiState.selectedObject }?.name
                            ?: "Select habitat", fontWeight = FontWeight.Bold
                    )
                }

                // Name
                Label("Animal name (optional):")
                OutlinedTextField(
                    value = uiState.name,
                    onValueChange = { newValue -> viewModel.updateState { it.copy(name = newValue) } },
                    placeholder = { Text("Enter animal name...") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    )
                )

                // Birth Date
                Label("Birth date:")
                val datePickerState = rememberDatePickerState()
                var showDatePicker by remember { mutableStateOf(false) }
                Box(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = uiState.birthDate,
                        onValueChange = {},
                        readOnly = true,
                        placeholder = { Text("Enter birth date (DD-MM-YYYY)...") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        trailingIcon = {
                            Icon(
                                Icons.Default.DateRange,
                                contentDescription = null,
                                modifier = Modifier.clickable { showDatePicker = true })
                        })
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clickable { showDatePicker = true })
                }
                if (showDatePicker) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePicker = false },
                        confirmButton = {
                            TextButton(onClick = {
                                datePickerState.selectedDateMillis?.let { millis ->
                                    val date = java.time.Instant.ofEpochMilli(millis)
                                        .atZone(java.time.ZoneId.systemDefault()).toLocalDate()
                                    val formattedDate = "%02d-%02d-%04d".format(
                                        date.dayOfMonth, date.monthValue, date.year
                                    )
                                    viewModel.updateState { it.copy(birthDate = formattedDate) }
                                }
                                showDatePicker = false
                            }) { Text("OK") }
                        },
                        dismissButton = {
                            TextButton(onClick = {
                                showDatePicker = false
                            }) { Text("Cancel") }
                        }) { DatePicker(state = datePickerState) }
                }

                // Gender
                Label("Pick gender:")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    GenderButton(
                        "Male", uiState.gender == "Male"
                    ) {
                        viewModel.updateState { it.copy(gender = "Male") }
                    }
                    GenderButton(
                        "Female", uiState.gender == "Female"
                    ) {
                        viewModel.updateState { it.copy(gender = "Female") }
                    }
                    GenderButton(
                        "Not Sexed", uiState.gender == "Not Sexed"
                    ) {
                        viewModel.updateState { it.copy(gender = "Not Sexed") }
                    }
                }

                // Size
                Label("Animal size:")
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ExposedDropdownMenuBox(
                        expanded = sizeTypeExp,
                        onExpandedChange = { sizeTypeExp = !sizeTypeExp },
                        modifier = Modifier.width(100.dp)
                    ) {
                        OutlinedTextField(
                            value = when (uiState.sizeType) {
                                0L -> "cm"; 1L -> "L"; else -> "other"
                            },
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier.menuAnchor(),
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline
                            ),
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = sizeTypeExp) })
                        ExposedDropdownMenu(
                            expanded = sizeTypeExp, onDismissRequest = { sizeTypeExp = false }) {
                            DropdownMenuItem(text = { Text("cm") }, onClick = {
                                viewModel.updateState { it.copy(sizeType = 0L) }
                                sizeTypeExp = false
                            })
                            DropdownMenuItem(text = { Text("L") }, onClick = {
                                viewModel.updateState { it.copy(sizeType = 1L) }
                                sizeTypeExp = false
                            })
                            DropdownMenuItem(text = { Text("other") }, onClick = {
                                viewModel.updateState { it.copy(sizeType = 2L) }
                                sizeTypeExp = false
                            })
                        }
                    }
                    Spacer(Modifier.width(8.dp))
                    OutlinedTextField(
                        value = uiState.size,
                        onValueChange = { newValue -> viewModel.updateState { it.copy(size = newValue) } },
                        placeholder = { Text("Enter animal size...") },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        )
                    )
                }

                // Photo
                Label("Animal photo (optional):")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { photoPicker.launchGallery() },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        ),
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp, MaterialTheme.colorScheme.outline
                        )
                    ) {
                        Text("Gallery", fontWeight = FontWeight.Bold)
                    }
                    Button(
                        onClick = { photoPicker.launchCamera() },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        ),
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp, MaterialTheme.colorScheme.outline
                        )
                    ) {
                        Text("Camera", fontWeight = FontWeight.Bold)
                    }
                }
                if (uiState.photo != null) {
                    PhotoFromByteArray(
                        bytes = uiState.photo,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }

                // Notes
                Label("Additional notes (optional):")
                OutlinedTextField(
                    value = uiState.notes,
                    onValueChange = { newValue -> viewModel.updateState { it.copy(notes = newValue) } },
                    placeholder = { Text("Enter notes...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 60.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    )
                )

                Spacer(Modifier.height(16.dp))

                // Accept Button
                Button(
                    onClick = {
                        val objId = uiState.selectedObject
                        val specId = uiState.selectedSpecies
                        if (objId != null && specId != null) {
                            viewModel.upsertAnimal(
                                Animal(
                                    id = animalId ?: 0L,
                                    objectId = objId,
                                    speciesId = specId,
                                    name = uiState.name.ifBlank { null },
                                    gender = uiState.gender.ifBlank { null },
                                    birthDate = uiState.birthDate.ifBlank { null },
                                    lastFeeding = viewModel.lastFeeding,
                                    lastSpray = viewModel.lastSpray,
                                    lastMolt = viewModel.lastMolt,
                                    size = uiState.size.toLongOrNull(),
                                    sizeType = uiState.sizeType,
                                    notes = uiState.notes.ifBlank { null },
                                    photo = uiState.photo
                                )
                            )
                            viewModel.clearForm()
                            onBack()
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 32.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(24.dp),
                    enabled = uiState.selectedObject != null && uiState.selectedSpecies != null
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(
                            "ACCEPT",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(Modifier.width(8.dp))
                        Icon(
                            Icons.Default.Check,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

                // Keyboard spacer
                Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.ime))

            }
        }

        if (showDeleteDialog && animalId != null) {
            DeleteConfirmationDialog(onDismiss = { showDeleteDialog = false },
                onConfirm = {
                    viewModel.deleteAnimal(animalId)
                    showDeleteDialog = false
                    onBack()
                })
        }
    }
}
