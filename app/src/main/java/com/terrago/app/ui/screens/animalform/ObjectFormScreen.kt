package com.terrago.app.ui.screens.animalform

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.terrago.app.ui.components.Label
import com.terrago.app.ui.components.topbar.TopActionsBar
import com.terrago.app.viewmodel.animalformviewmodel.AnimalFormViewModel
import com.terrago.app.ui.components.TerrariumCard


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

    val objects by viewModel.availableObjects.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFEFFFEF))) {
        TopActionsBar(onBackClick = onBack)
        Column(
            modifier = Modifier
            //    .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Label("Choose terrarium:")
            Spacer(Modifier.height(16.dp))

            //Objects List
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 225.dp)
            ) {
                items(
                    items = objects,
                    key = { it.object_id }
                ) { obj ->
                    TerrariumCard(
                        name = obj.name,
                        width = obj.width,
                        length = obj.length,
                        height = obj.height,
                        description = obj.description,
                        location_name =obj.location_name,
                        onClick = {
                            viewModel.selectedObject = obj.object_id
                            onBack()
                        }
                    )
                }
            }

            Spacer(Modifier.height(12.dp))
            Column() {
                Label("or add a new one:")
                Spacer(Modifier.height(16.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text("Enter terrarium name...") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                        Label("Width:")
                        Spacer(Modifier.height(16.dp))
                        OutlinedTextField(
                            value = width,
                            onValueChange = { width = it },
                            modifier = Modifier.fillMaxWidth()
                        )

                    }
                    Column(modifier = Modifier.weight(1f).padding(horizontal = 8.dp)) {
                        Label("Length:")
                        Spacer(Modifier.height(16.dp))
                        OutlinedTextField(
                            value = length,
                            onValueChange = { length = it },
                            modifier = Modifier.fillMaxWidth()
                        )

                    }
                    Column(modifier = Modifier.weight(1f).padding(start = 8.dp)) {
                        Label("Height:")
                        Spacer(Modifier.height(16.dp))
                        OutlinedTextField(
                            value = height,
                            onValueChange = { height = it },
                            modifier = Modifier.fillMaxWidth()
                        )

                    }
                }
                Spacer(Modifier.height(12.dp))

                Label("Description (optional):")
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = { Text("Enter terrarium description...") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))

                Label("Location name:")
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = locationName,
                    onValueChange = { locationName = it },
                    placeholder = { Text("Enter location name...") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))

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
    }
}

