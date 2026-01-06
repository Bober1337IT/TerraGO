package com.terrago.app.ui.screens.animalform

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.terrago.app.R
import com.terrago.app.ui.components.Label
import com.terrago.app.ui.theme.TerraGOTheme
import com.terrago.app.viewmodel.animalformviewmodel.AnimalFormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpeciesFormScreen(viewModel: AnimalFormViewModel, onBack: () -> Unit) {
    // Form State matching species.sq
    var latinName by remember { mutableStateOf("") }
    var commonName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var tempMin by remember { mutableStateOf("") }
    var tempMax by remember { mutableStateOf("") }
    var humMin by remember { mutableStateOf("") }
    var humMax by remember { mutableStateOf("") }
    var lightCycle by remember { mutableStateOf("") }

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
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                modifier = Modifier
                                    .size(36.dp)
                                    .border(2.dp, MaterialTheme.colorScheme.onSurface, CircleShape)
                                    .padding(4.dp)
                            )
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
                // Latin Name
                Column {
                    Label("Species name - latin:")
                    OutlinedTextField(
                        value = latinName,
                        onValueChange = { latinName = it },
                        placeholder = { Text("Enter species name...") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        )
                    )
                }

                // Common Name
                Column {
                    Label("Species name - common (optional):")
                    OutlinedTextField(
                        value = commonName,
                        onValueChange = { commonName = it },
                        placeholder = { Text("Enter species name...") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        )
                    )
                }

                // Temperature
                Column {
                    Label("Temperature:")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedTextField(
                            value = tempMin,
                            onValueChange = { tempMin = it },
                            placeholder = { Text("Enter minimum...") },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline
                            )
                        )
                        OutlinedTextField(
                            value = tempMax,
                            onValueChange = { tempMax = it },
                            placeholder = { Text("Enter maximum...") },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline
                            )
                        )
                    }
                }

                // Humidity
                Column {
                    Label("Humidity:")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedTextField(
                            value = humMin,
                            onValueChange = { humMin = it },
                            placeholder = { Text("Enter minimum...") },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline
                            )
                        )
                        OutlinedTextField(
                            value = humMax,
                            onValueChange = { humMax = it },
                            placeholder = { Text("Enter maximum...") },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline
                            )
                        )
                    }
                }

                // Lighting
                Column {
                    Label("Lighting hours:")
                    OutlinedTextField(
                        value = lightCycle,
                        onValueChange = { lightCycle = it },
                        placeholder = { Text("Enter amount of lighting hours...") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        )
                    )
                }

                // Description
                Column {
                    Label("Description (optional):")
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        placeholder = { Text("Enter description...") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        )
                    )
                }

                Spacer(Modifier.height(16.dp))

                // Accept Button
                Button(
                    onClick = {
                        viewModel.insertSpecies(
                            latinName = latinName,
                            commonName = commonName.ifBlank { null },
                            description = description.ifBlank { null },
                            temperatureMin = tempMin.ifBlank { null }?.toDoubleOrNull(),
                            temperatureMax = tempMax.ifBlank { null }?.toDoubleOrNull(),
                            humidityMin = humMin.ifBlank { null }?.toDoubleOrNull(),
                            humidityMax = humMax.ifBlank { null }?.toDoubleOrNull(),
                            lightCycleH = lightCycle.ifBlank { null }?.toLongOrNull()
                        )
                        onBack()
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 32.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(24.dp),
                    enabled = latinName.isNotBlank()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text("ACCEPT", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary)
                        Spacer(Modifier.width(8.dp))
                        Icon(Icons.Default.Check, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }
        }
    }
}
