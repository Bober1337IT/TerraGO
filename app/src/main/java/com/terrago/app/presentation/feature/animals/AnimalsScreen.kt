package com.terrago.app.presentation.feature.animals

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.terrago.app.presentation.shared.components.UpdateSizeDialog
import com.terrago.app.presentation.shared.theme.TerraGOTheme
import com.terrago.app.presentation.shared.components.enumclasses.ListAction
import com.terrago.app.presentation.feature.animals.components.*
import com.terrago.app.presentation.feature.animals.components.topbar.TopActionsBarWithIcons


@Composable
fun AnimalsScreen(
    viewModel: AnimalsViewModel = hiltViewModel(),
    onAnimalClick: (Long) -> Unit,
    onAddAnimalClick: () -> Unit
) {
    val animals by viewModel.animalsPreview.collectAsStateWithLifecycle()
    var currentAction by remember { mutableStateOf(ListAction.NAVIGATE) }

    var showSizeDialog by remember { mutableStateOf<Long?>(null) }
    var newSizeText by remember { mutableStateOf("") }

    TerraGOTheme(dynamicColor = false) {
        Scaffold(
            modifier = Modifier.fillMaxSize(), topBar = {
            Surface(shadowElevation = 4.dp) {
                TopActionsBarWithIcons(
                    currentAction = currentAction, onActionSelected = { action ->
                        currentAction =
                            if (currentAction == action) ListAction.NAVIGATE else action
                    })
            }
        }, floatingActionButton = {
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
        }, containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                if (animals.isEmpty()) {
                    EmptyAnimalsState(
                        onAddClick = onAddAnimalClick
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(
                            items = animals, key = { it.animalId }) { animal ->
                            AnimalItem(
                                animal = animal, onClick = { id ->
                                    when (currentAction) {
                                        ListAction.NAVIGATE -> onAnimalClick(
                                            id
                                        )

                                        ListAction.FEED -> {
                                            viewModel.setLastFeeding(id)
                                            currentAction =
                                                ListAction.NAVIGATE
                                        }

                                        ListAction.SPRAY -> {
                                            viewModel.setLastSpray(id)
                                            currentAction =
                                                ListAction.NAVIGATE
                                        }

                                        ListAction.MOLT -> {
                                            if (animal.sizeType == 1L) {
                                                viewModel.setLastMolt(id)
                                                currentAction =
                                                    ListAction.NAVIGATE
                                            } else {
                                                showSizeDialog = id
                                                newSizeText = animal.size?.toString() ?: ""
                                            }
                                        }
                                    }
                                })
                        }

                        item {
                            Spacer(modifier = Modifier.height(50.dp))
                        }
                    }
                }
            }
        }

        if (showSizeDialog != null) {
            UpdateSizeDialog(initialSize = newSizeText,
                onDismiss = {
                    currentAction =
                        ListAction.NAVIGATE
                    showSizeDialog = null
                },
                onConfirm = { newSize ->
                    showSizeDialog?.also { id ->
                        viewModel.setSize(id, newSize)
                    }
                    currentAction =
                        ListAction.NAVIGATE
                    showSizeDialog = null
                })
        }
    }
}
