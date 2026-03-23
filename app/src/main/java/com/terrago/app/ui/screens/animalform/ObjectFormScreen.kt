package com.terrago.app.ui.screens.animalform

import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.terrago.app.R
import com.terrago.app.ui.screens.animalform.components.Label
import com.terrago.app.ui.screens.animalform.components.TerrariumCard
import com.terrago.app.ui.theme.TerraGOTheme
import com.terrago.app.viewmodel.animalformviewmodel.AnimalFormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObjectFormScreen(
    viewModel: AnimalFormViewModel, onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val objects by viewModel.availableObjects.collectAsStateWithLifecycle()

    val sortedObjects = remember(objects) {
        objects.sortedBy { it.name }
    }

    val clearFields = {
        uiState.editingObjectId = null
        viewModel.clearObjectFields()
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
                    }, actions = {
                        if (uiState.editingObjectId != null) {
                            Button(
                                onClick = {
                                    viewModel.deleteObject(uiState.editingObjectId!!)
                                    clearFields()
                                },
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
                // Choose existing
                Label("Choose terrarium:")

                val chunks = sortedObjects.chunked(2)
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
                                    locationName = obj.location_name,
                                    isSelected = uiState.editingObjectId == obj.object_id,
                                    onClick = {
                                        viewModel.updateState { it.copy(selectedObject = obj.object_id) }
                                        onBack()
                                    },
                                    onLongClick = {
                                        uiState.editingObjectId = obj.object_id
                                        viewModel.updateState {
                                            it.copy(
                                                objectName = obj.name,
                                                objectWidth = obj.width?.toString() ?: "",
                                                objectLength = obj.length?.toString() ?: "",
                                                objectHeight = obj.height?.toString() ?: "",
                                                objectLocationName = obj.location_name ?: "",
                                                objectDescription = obj.description ?: ""
                                            )
                                        }
                                    })
                            }
                        }
                        if (rowItems.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))
                Label(if (uiState.editingObjectId == null) "or add new one:" else "update selected one:")

                // Name
                OutlinedTextField(
                    value = uiState.objectName,
                    onValueChange = { newValue ->
                        viewModel.updateState {
                            it.copy(
                                objectName = newValue
                            )
                        }
                    },
                    placeholder = { Text("Enter terrarium name...") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
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
                            value = uiState.objectWidth,
                            onValueChange = { newValue ->
                                viewModel.updateState {
                                    it.copy(
                                        objectWidth = newValue
                                    )
                                }
                            },
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
                    Column(modifier = Modifier.weight(1f)) {
                        Label("Length:")
                        OutlinedTextField(
                            value = uiState.objectLength,
                            onValueChange = { newValue ->
                                viewModel.updateState {
                                    it.copy(
                                        objectLength = newValue
                                    )
                                }
                            },
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
                    Column(modifier = Modifier.weight(1f)) {
                        Label("Height:")
                        OutlinedTextField(
                            value = uiState.objectHeight,
                            onValueChange = { newValue ->
                                viewModel.updateState {
                                    it.copy(
                                        objectHeight = newValue
                                    )
                                }
                            },
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
                }

                // Description
                Column {
                    Label("Description (optional):")
                    OutlinedTextField(
                        value = uiState.objectDescription,
                        onValueChange = { newValue ->
                            viewModel.updateState {
                                it.copy(
                                    objectDescription = newValue
                                )
                            }
                        },
                        placeholder = { Text("Enter terrarium description...") },
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

                // Location
                Column {
                    Label("Location name:")
                    OutlinedTextField(
                        value = uiState.objectLocationName,
                        onValueChange = { newValue ->
                            viewModel.updateState {
                                it.copy(
                                    objectLocationName = newValue
                                )
                            }
                        },
                        placeholder = { Text("Enter location name...") },
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
                        if (uiState.editingObjectId == null) {
                            viewModel.insertObject(
                                name = uiState.objectName,
                                description = uiState.objectDescription.ifBlank { null },
                                length = uiState.objectLength.ifBlank { null }?.toLongOrNull(),
                                width = uiState.objectWidth.ifBlank { null }?.toLongOrNull(),
                                height = uiState.objectHeight.ifBlank { null }?.toLongOrNull(),
                                location = uiState.objectLocationName.ifBlank { null })
                        } else {
                            viewModel.updateObject(
                                objectId = uiState.editingObjectId!!,
                                name = uiState.objectName,
                                description = uiState.objectDescription.ifBlank { null },
                                length = uiState.objectLength.ifBlank { null }?.toLongOrNull(),
                                width = uiState.objectWidth.ifBlank { null }?.toLongOrNull(),
                                height = uiState.objectHeight.ifBlank { null }?.toLongOrNull(),
                                location = uiState.objectLocationName.ifBlank { null })
                        }
                        if (uiState.editingObjectId == null) {
                            onBack()
                        } else {
                            clearFields()
                        }

                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 32.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(24.dp),
                    enabled = uiState.objectName.isNotBlank()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(
                            if (uiState.editingObjectId == null) "ACCEPT" else "UPDATE",
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
