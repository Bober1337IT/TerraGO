package com.terrago.app.ui.screens.animalform

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.terrago.app.ui.components.TerrariumCard
import com.terrago.app.ui.theme.TerraGOTheme
import com.terrago.app.viewmodel.animalformviewmodel.AnimalFormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObjectFormScreen(
    viewModel: AnimalFormViewModel,
    onBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var locationName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var length by remember { mutableStateOf("") }
    var width by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    val objects by viewModel.availableObjects.collectAsState()

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
                                    .border(2.dp, Color.Black, CircleShape)
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
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Choose existing
                Label("Choose terrarium:")
                
                // Using a Column with Row instead of Grid inside Scrollable to avoid nested scroll issues
                val chunks = objects.chunked(2)
                chunks.forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        rowItems.forEach { obj ->
                            Box(modifier = Modifier.weight(1f)) {
                                TerrariumCard(
                                    name = obj.name,
                                    width = obj.width,
                                    length = obj.length,
                                    height = obj.height,
                                    description = obj.description,
                                    location_name = obj.location_name,
                                    onClick = {
                                        viewModel.selectedObject = obj.object_id
                                        onBack()
                                    }
                                )
                            }
                        }
                        if (rowItems.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))
                Label("or add new one:")

                // Name
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text("Enter terrarium name...") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black
                    )
                )

                // Dimensions
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Label("Width:")
                        OutlinedTextField(
                            value = width,
                            onValueChange = { width = it },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Black
                            )
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Label("Length:")
                        OutlinedTextField(
                            value = length,
                            onValueChange = { length = it },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Black
                            )
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Label("Height:")
                        OutlinedTextField(
                            value = height,
                            onValueChange = { height = it },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Black
                            )
                        )
                    }
                }

                // Description
                Column {
                    Label("Description (optional):")
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        placeholder = { Text("Enter terrarium description...") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        )
                    )
                }

                // Location
                Column {
                    Label("Location name:")
                    OutlinedTextField(
                        value = locationName,
                        onValueChange = { locationName = it },
                        placeholder = { Text("Enter location name...") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        )
                    )
                }

                Spacer(Modifier.height(16.dp))

                // Accept Button
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
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 32.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
                    shape = RoundedCornerShape(24.dp),
                    enabled = name.isNotBlank()
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
