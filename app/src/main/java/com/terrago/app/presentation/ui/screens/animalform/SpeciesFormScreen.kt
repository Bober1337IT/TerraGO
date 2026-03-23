package com.terrago.app.presentation.ui.screens.animalform

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.terrago.app.R
import com.terrago.app.presentation.ui.screens.animalform.components.Label
import com.terrago.app.presentation.ui.theme.TerraGOTheme
import com.terrago.app.presentation.viewmodel.animalformviewmodel.AnimalFormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpeciesFormScreen(viewModel: AnimalFormViewModel, onBack: () -> Unit) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
                // Latin Name
                Column {
                    Label("Species name - latin:")
                    OutlinedTextField(
                        value = uiState.speciesLatinName,
                        onValueChange = { newValue ->
                            viewModel.updateState { it.copy(speciesLatinName = newValue) }
                        },
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
                        value = uiState.speciesCommonName,
                        onValueChange = { newValue ->
                            viewModel.updateState { it.copy(speciesCommonName = newValue) }
                        },
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
                            value = uiState.speciesTempMin,
                            onValueChange = { newValue ->
                                viewModel.updateState { it.copy(speciesTempMin = newValue) }
                            },
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
                            value = uiState.speciesTempMax,
                            onValueChange = { newValue ->
                                viewModel.updateState { it.copy(speciesTempMax = newValue) }
                            },
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
                            value = uiState.speciesHumMin,
                            onValueChange = { newValue ->
                                viewModel.updateState { it.copy(speciesHumMin = newValue) }
                            },
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
                            value = uiState.speciesHumMax,
                            onValueChange = { newValue ->
                                viewModel.updateState { it.copy(speciesHumMax = newValue) }
                            },
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
                        value = uiState.speciesLightCycle,
                        onValueChange = { newValue ->
                            viewModel.updateState { it.copy(speciesLightCycle = newValue) }
                        },
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
                        value = uiState.speciesDescription,
                        onValueChange = { newValue ->
                            viewModel.updateState { it.copy(speciesDescription = newValue) }
                        },
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
                            latinName = uiState.speciesLatinName,
                            commonName = uiState.speciesCommonName.ifBlank { null },
                            description = uiState.speciesDescription.ifBlank { null },
                            temperatureMin = uiState.speciesTempMin.ifBlank { null }
                                ?.toDoubleOrNull(),
                            temperatureMax = uiState.speciesTempMax.ifBlank { null }
                                ?.toDoubleOrNull(),
                            humidityMin = uiState.speciesHumMin.ifBlank { null }?.toDoubleOrNull(),
                            humidityMax = uiState.speciesHumMax.ifBlank { null }?.toDoubleOrNull(),
                            lightCycleH = uiState.speciesLightCycle.ifBlank { null }?.toLongOrNull()
                        )
                        onBack()

                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 32.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(24.dp),
                    enabled = uiState.speciesLatinName.isNotBlank()
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
    }
}
