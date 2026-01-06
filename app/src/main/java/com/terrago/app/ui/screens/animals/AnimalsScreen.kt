package com.terrago.app.ui.screens.animals

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.terrago.app.ui.components.AnimalItem
import com.terrago.app.ui.components.topbar.TopActionsBarWithIcons
import com.terrago.app.ui.theme.TerraGOTheme
import com.terrago.app.viewmodel.animalsviewmodel.AnimalsViewModel

enum class ListAction {
    NAVIGATE, FEED, SPRAY, MOLT
}

@Composable
fun AnimalsScreen(
    viewModel: AnimalsViewModel,
    onAnimalClick: (Long) -> Unit,
    onAddAnimalClick: () -> Unit
) {
    val animals by viewModel.animalsPreview.collectAsState()
    var currentAction by remember { mutableStateOf(ListAction.NAVIGATE) }
    
    var showSizeDialog by remember { mutableStateOf<Long?>(null) }
    var newSizeText by remember { mutableStateOf("") }

    TerraGOTheme(dynamicColor = false) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Surface(shadowElevation = 4.dp) {
                    TopActionsBarWithIcons(
                        currentAction = currentAction,
                        onActionSelected = { action ->
                            currentAction = if (currentAction == action) ListAction.NAVIGATE else action
                        }
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = onAddAnimalClick,
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.navigationBarsPadding()
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add animal",
                        modifier = Modifier.size(36.dp)
                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                if (animals.isEmpty()) {
                    EmptyAnimalsState(onAddAnimalClick)
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(
                            items = animals,
                            key = { it.animalId }
                        ) { animal ->
                            AnimalItem(
                                animal = animal,
                                onClick = { id ->
                                    when (currentAction) {
                                        ListAction.NAVIGATE -> onAnimalClick(id)
                                        ListAction.FEED -> {
                                            viewModel.setLastFeeding(id)
                                            currentAction = ListAction.NAVIGATE
                                        }
                                        ListAction.SPRAY -> {
                                            viewModel.setLastSpray(id)
                                            currentAction = ListAction.NAVIGATE
                                        }
                                        ListAction.MOLT -> {
                                            if (animal.sizeType == 1L) {
                                                // Automatic molt for spiders (L stage)
                                                viewModel.setLastMolt(id)
                                                currentAction = ListAction.NAVIGATE
                                            } else {
                                                // Open dialog for cm/other
                                                showSizeDialog = id
                                                newSizeText = animal.size?.toString() ?: ""
                                                // We don't reset action here yet, wait for dialog confirm
                                            }
                                        }
                                    }
                                }
                            )
                        }
                        
                        item {
                            Spacer(modifier = Modifier.height(100.dp))
                        }
                    }
                }
            }
        }
        
        if (showSizeDialog != null) {
            AlertDialog(
                onDismissRequest = { showSizeDialog = null },
                title = { Text("Update Size") },
                text = {
                    OutlinedTextField(
                        value = newSizeText,
                        onValueChange = { newSizeText = it },
                        label = { Text("New size") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        newSizeText.toLongOrNull()?.let {
                            viewModel.setSize(showSizeDialog!!, it)
                        }
                        showSizeDialog = null
                        currentAction = ListAction.NAVIGATE
                    }) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { 
                        showSizeDialog = null
                        currentAction = ListAction.NAVIGATE
                    }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Composable
private fun EmptyAnimalsState(onAddClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = "🌿",
                fontSize = 64.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Your collection is empty",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Start adding your first animal habitat!",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onAddClick,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Add New Animal")
            }
        }
    }
}
