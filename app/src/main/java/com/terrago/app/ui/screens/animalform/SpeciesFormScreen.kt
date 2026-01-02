package com.terrago.app.ui.screens.animalform

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.terrago.app.ui.components.topbar.TopActionsBar
import com.terrago.app.viewmodel.animalformviewmodel.AnimalFormViewModel
import com.terrago.app.ui.components.Label

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

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFEFFFEF))) {
        TopActionsBar(onBackClick = onBack)
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            Label("Species name - latin:")
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = latinName,
                onValueChange = { latinName = it },
                placeholder = { Text("Enter species name...") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))

            Label("Species name - common (optional):")
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = commonName,
                onValueChange = { commonName = it },
                placeholder = { Text("Enter species name...") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))

            Label("Temperature:")
            Spacer(Modifier.height(8.dp))
            Row(modifier=Modifier.fillMaxWidth()){
                OutlinedTextField(
                    value = tempMin,
                    onValueChange = { tempMin = it },
                    placeholder = { Text("Enter minimum...") },
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )
                OutlinedTextField(
                    value = tempMax,
                    onValueChange = { tempMax = it },
                    placeholder = { Text("Enter maximum...") },
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )
            }
            Spacer(Modifier.height(12.dp))

            Label("Humidity:")
            Spacer(Modifier.height(8.dp))
            Row(modifier=Modifier.fillMaxWidth()){
                OutlinedTextField(
                    value = humMin,
                    onValueChange = { humMin = it },
                    placeholder = { Text("Enter minimum...") },
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )
                OutlinedTextField(
                    value = humMax,
                    onValueChange = { humMax = it },
                    placeholder = { Text("Enter maximum...") },
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )
            }
            Spacer(Modifier.height(12.dp))

            Label("Lighting hours:")
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = lightCycle,
                onValueChange = { lightCycle = it },
                placeholder = { Text("Enter amount of lighting hours...") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))

            Label("Description (optional):")
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                placeholder = { Text("Enter description...") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))

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
            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}
