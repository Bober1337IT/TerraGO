package com.terrago.app.ui.screens.animalform

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terrago.app.viewmodel.animalformviewmodel.AnimalFormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpeciesFormScreen(viewModel: AnimalFormViewModel, onBack: () -> Unit) {
    var commonName by remember { mutableStateOf("") }
    var latinName by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Add New Species") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = commonName,
                onValueChange = { commonName = it },
                label = { Text("Common Name (Required)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = latinName,
                onValueChange = { latinName = it },
                label = { Text("Latin Name (Optional)") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    viewModel.insertSpecies(commonName, latinName.ifBlank { null })
                    onBack()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = commonName.isNotBlank()
            ) {
                Text("Save Species")
            }
        }
    }
}
