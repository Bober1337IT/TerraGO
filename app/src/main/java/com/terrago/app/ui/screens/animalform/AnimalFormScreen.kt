package com.terrago.app.ui.screens.animalform

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.terrago.app.R
import com.terrago.app.navigation.graph.routes.AnimalFormRoutes
import com.terrago.app.ui.components.Label
import com.terrago.app.ui.components.photo.PhotoFromByteArray
import com.terrago.app.ui.components.photo.rememberPhotoPicker
import com.terrago.app.ui.theme.TerraGOTheme
import com.terrago.app.viewmodel.animalformviewmodel.AnimalFormViewModel
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

    var specExp by remember { mutableStateOf(false) }
    var sizeTypeExp by remember { mutableStateOf(false) }

    val photoPicker = rememberPhotoPicker { bytes ->
        viewModel.photo = bytes
    }

    // Handle system back button
    BackHandler {
        viewModel.clearForm()
        onBack()
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
                viewModel.photo = it.photo
            }
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
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            viewModel.clearForm()
                            onBack()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                modifier = Modifier
                                    .size(36.dp)
                                    .border(2.dp, Color.Black, CircleShape)
                                    .padding(4.dp)
                            )
                        }
                    },
                    actions = {
                        if (animalId != null) {
                            Button(
                                onClick = {
                                    viewModel.deleteAnimal(animalId)
                                    onBack()
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                                shape = RoundedCornerShape(24.dp),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp)
                            ) {
                                Text("DELETE", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                Spacer(Modifier.width(4.dp))
                                Icon(Icons.Default.Close, contentDescription = null, modifier = Modifier.size(16.dp))
                            }
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            },
            containerColor = MaterialTheme.colorScheme.background
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Species
                Label("Animal species:")
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ExposedDropdownMenuBox(
                        expanded = specExp,
                        onExpandedChange = { specExp = !specExp },
                        modifier = Modifier.weight(1f)
                    ) {
                        OutlinedTextField(
                            value = species.find { it.species_id == viewModel.selectedSpecies }?.name_latin ?: "Choose animal species...",
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier.menuAnchor().fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Black
                            ),
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = specExp) }
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
                        }
                    }
                    Spacer(Modifier.width(8.dp))
                    IconButton(
                        onClick = { navController.navigate(AnimalFormRoutes.NEW_SPECIES) },
                        modifier = Modifier.size(48.dp).background(Color(0xFF2E7D32), CircleShape)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add Species", tint = Color.White)
                    }
                }

                // Habitat
                Label("Choose Habitat:")
                Button(
                    onClick = { navController.navigate(AnimalFormRoutes.NEW_HABITAT) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color.Black)
                ) {
                    Text(
                        text = objects.find { it.object_id == viewModel.selectedObject }?.name ?: "Select habitat",
                        fontWeight = FontWeight.Bold
                    )
                }

                // Name
                Label("Animal name (optional):")
                OutlinedTextField(
                    value = viewModel.name,
                    onValueChange = { viewModel.name = it },
                    placeholder = { Text("Enter animal name...") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black
                    )
                )

                // Birth Date
                Label("Birth date:")
                val datePickerState = rememberDatePickerState()
                var showDatePicker by remember { mutableStateOf(false) }
                Box(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = viewModel.birthDate,
                        onValueChange = {},
                        readOnly = true,
                        placeholder = { Text("Enter birth date (DD-MM-YYYY)...") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        ),
                        trailingIcon = { Icon(Icons.Default.DateRange, contentDescription = null, modifier = Modifier.clickable { showDatePicker = true }) }
                    )
                    Box(modifier = Modifier.matchParentSize().clickable { showDatePicker = true })
                }
                if (showDatePicker) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePicker = false },
                        confirmButton = {
                            TextButton(onClick = {
                                datePickerState.selectedDateMillis?.let { millis ->
                                    val date = java.time.Instant.ofEpochMilli(millis).atZone(java.time.ZoneId.systemDefault()).toLocalDate()
                                    viewModel.birthDate = "%02d-%02d-%04d".format(date.dayOfMonth, date.monthValue, date.year)
                                }
                                showDatePicker = false
                            }) { Text("OK") }
                        },
                        dismissButton = { TextButton(onClick = { showDatePicker = false }) { Text("Cancel") } }
                    ) { DatePicker(state = datePickerState) }
                }

                // Gender
                Label("Pick gender:")
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    GenderButton("Male", viewModel.gender == "Male") { viewModel.gender = "Male" }
                    GenderButton("Female", viewModel.gender == "Female") { viewModel.gender = "Female" }
                    GenderButton("Not Sexed", viewModel.gender == "Not Sexed") { viewModel.gender = "Not Sexed" }
                }

                // Size
                Label("Animal size:")
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    ExposedDropdownMenuBox(
                        expanded = sizeTypeExp,
                        onExpandedChange = { sizeTypeExp = !sizeTypeExp },
                        modifier = Modifier.width(100.dp)
                    ) {
                        OutlinedTextField(
                            value = when (viewModel.sizeType) { 0L -> "cm"; 1L -> "L"; else -> "other" },
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier.menuAnchor(),
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Black
                            ),
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = sizeTypeExp) }
                        )
                        ExposedDropdownMenu(expanded = sizeTypeExp, onDismissRequest = { sizeTypeExp = false }) {
                            DropdownMenuItem(text = { Text("cm") }, onClick = { viewModel.sizeType = 0; sizeTypeExp = false })
                            DropdownMenuItem(text = { Text("L") }, onClick = { viewModel.sizeType = 1; sizeTypeExp = false })
                            DropdownMenuItem(text = { Text("other") }, onClick = { viewModel.sizeType = 2; sizeTypeExp = false })
                        }
                    }
                    Spacer(Modifier.width(8.dp))
                    OutlinedTextField(
                        value = viewModel.size,
                        onValueChange = { viewModel.size = it },
                        placeholder = { Text("Enter animal size...") },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        )
                    )
                }

                // Photo
                Label("Animal photo (optional):")
                Button(
                    onClick = { photoPicker.launchGallery() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color.Black)
                ) {
                    Text("Select image", fontWeight = FontWeight.Bold)
                }
                if (viewModel.photo != null) {
                    PhotoFromByteArray(bytes = viewModel.photo, modifier = Modifier.size(120.dp).clip(RoundedCornerShape(8.dp)))
                }

                // Notes
                Label("Additional notes (optional):")
                OutlinedTextField(
                    value = viewModel.notes,
                    onValueChange = { viewModel.notes = it },
                    placeholder = { Text("Enter notes...") },
                    modifier = Modifier.fillMaxWidth().heightIn(min = 60.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black
                    )
                )

                Spacer(Modifier.height(16.dp))

                // Accept Button
                Button(
                    onClick = {
                        val objId = viewModel.selectedObject
                        val specId = viewModel.selectedSpecies
                        if (objId != null && specId != null) {
                            viewModel.insertAnimal(
                                animalId = animalId,
                                objectId = objId,
                                speciesId = specId,
                                name = viewModel.name.ifBlank { null },
                                gender = viewModel.gender.ifBlank { null },
                                birthDate = viewModel.birthDate.ifBlank { null },
                                lastFeeding = null,
                                lastSpray = null,
                                lastMolt = null,
                                size = viewModel.size.toLongOrNull(),
                                sizeType = viewModel.sizeType,
                                notes = viewModel.notes.ifBlank { null },
                                photo = viewModel.photo
                            )
                            viewModel.clearForm()
                            onBack()
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 32.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
                    shape = RoundedCornerShape(24.dp),
                    enabled = viewModel.selectedObject != null && viewModel.selectedSpecies != null
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text("ACCEPT", fontWeight = FontWeight.Bold, color = Color.White)
                        Spacer(Modifier.width(8.dp))
                        Icon(Icons.Default.Check, contentDescription = null, tint = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
private fun GenderButton(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = Modifier
            .width(100.dp)
            .height(36.dp),
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) Color.Black else Color.White,
        border = if (!isSelected) androidx.compose.foundation.BorderStroke(1.dp, Color.Black) else null
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = label,
                color = if (isSelected) Color.White else Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
}
