package com.terrago.app.ui.screens.animalform

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        // Names
        TextField(
            value = latinName,
            onValueChange = { latinName = it },
            label = { Text("Latin Name (Required)") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = commonName,
            onValueChange = { commonName = it },
            label = { Text("Common Name") },
            modifier = Modifier.fillMaxWidth()
        )

        // Description
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        // Temperature Row
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = tempMin,
                onValueChange = { tempMin = it },
                label = { Text("Temp Min") },
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = tempMax,
                onValueChange = { tempMax = it },
                label = { Text("Temp Max") },
                modifier = Modifier.weight(1f)
            )
        }

        // Humidity Row
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = humMin,
                onValueChange = { humMin = it },
                label = { Text("Hum Min") },
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = humMax,
                onValueChange = { humMax = it },
                label = { Text("Hum Max") },
                modifier = Modifier.weight(1f)
            )
        }

        // Light Cycle
        TextField(
            value = lightCycle,
            onValueChange = { lightCycle = it },
            label = { Text("Light Cycle (h)") },
            modifier = Modifier.fillMaxWidth()
        )

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
            modifier = Modifier.fillMaxWidth(),
            enabled = latinName.isNotBlank()
        ) {
            Text("Save Species")
        }
    }
}
