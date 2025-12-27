package com.terrago.app.ui.screens.animalform

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terrago.app.viewmodel.animalformviewmodel.AnimalFormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObjectFormScreen(
    viewModel: AnimalFormViewModel,
    onBack: () -> Unit
) {
    // Form State matching objects.sq
    var name by remember { mutableStateOf("") }
    var locationName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var length by remember { mutableStateOf("") }
    var width by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        // Basic Info
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Habitat Name (Required)") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = locationName,
            onValueChange = { locationName = it },
            label = { Text("Location Name") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        // Dimensions Row
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = length,
                onValueChange = { length = it },
                label = { Text("L") },
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = width,
                onValueChange = { width = it },
                label = { Text("W") },
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = height,
                onValueChange = { height = it },
                label = { Text("H") },
                modifier = Modifier.weight(1f)
            )
        }

        Button(
            onClick = {
                viewModel.insertObject(
                    name = name,
                    description = description.ifBlank { null },
                    length = length.ifBlank { null }?.toLongOrNull(),
                    width = width.ifBlank { null }?.toLongOrNull(),
                    height = height.ifBlank { null }?.toLongOrNull(),
                    location = locationName.ifBlank { null }
                )
                onBack()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = name.isNotBlank()
        ) {
            Text("Save Habitat")
        }
        Spacer(modifier = Modifier.height(64.dp))
    }
}

