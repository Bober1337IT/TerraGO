package com.terrago.app.ui.screens.animaldetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.terrago.app.R
import com.terrago.app.ui.theme.TerraGOTheme
import com.terrago.app.ui.components.photo.PhotoFromByteArray
import com.terrago.app.viewmodel.animalsviewmodel.AnimalsViewModel
import com.terrago.app.ui.components.enumclasses.PendingAction
import com.terrago.app.ui.screens.animaldetails.components.ActionConfirmationDialog
import com.terrago.app.ui.screens.animaldetails.components.ActionItem
import com.terrago.app.ui.components.UpdateSizeDialog
import com.terrago.app.ui.screens.animaldetails.components.ObjectExpandableInfo
import com.terrago.app.ui.screens.animaldetails.components.SpeciesExpandableInfo
import com.terrago.app.ui.screens.animaldetails.components.GenderIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalDetailsScreen(
    viewModel: AnimalsViewModel,
    animalId: Long,
    onBack: () -> Unit,
    onEditClick: (Long) -> Unit
) {
    val animal by viewModel.getAnimalDetails(animalId).collectAsState(initial = null)
    
    var showSizeDialog by remember { mutableStateOf(false) }
    var pendingAction by remember { mutableStateOf(PendingAction.NONE) }
    var newSizeText by remember { mutableStateOf("") }

    TerraGOTheme(dynamicColor = false) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "App Logo",
                            modifier = Modifier.height(40.dp)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(Color.Transparent, CircleShape)
                                    .padding(4.dp),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    },
                    actions = {
                        Button(
                            onClick = { onEditClick(animalId) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.onSurface,
                                contentColor = MaterialTheme.colorScheme.surface
                            ),
                            shape = RoundedCornerShape(20.dp),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("EDIT", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                Spacer(Modifier.width(4.dp))
                                Icon(
                                    Icons.Default.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            },
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) { padding ->
            Surface(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp),
                shape = RoundedCornerShape(24.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Photo
                    if (animal?.photo != null) {
                        PhotoFromByteArray(
                            bytes = animal?.photo,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Animal Name Header with Gender
                    if (!animal?.animalName.isNullOrBlank()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = animal?.animalName!!,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            
                            GenderIcon(
                                gender = animal?.gender,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }

                    // Species Name Header (Expandable) with Gender (if nickname is null)
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        val latinName = animal?.speciesLatinName ?: "Unknown Species"
                        
                        SpeciesExpandableInfo(
                            latinName = latinName,
                            commonName = animal?.speciesCommonName,
                            description = animal?.speciesDescription,
                            tempMin = animal?.speciesTempMin,
                            tempMax = animal?.speciesTempMax,
                            humMin = animal?.speciesHumMin,
                            humMax = animal?.speciesHumMax,
                            lightCycle = animal?.speciesLightCycle,
                            gender = if(animal?.animalName.isNullOrBlank()) {animal?.gender} else null
                        )
                    }

                    // Object Details (Expandable)
                    if (!animal?.objectName.isNullOrBlank()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        ObjectExpandableInfo(
                            name = animal?.objectName!!,
                            description = animal?.objectDescription,
                            length = animal?.objectLength,
                            width = animal?.objectWidth,
                            height = animal?.objectHeight,
                            location = animal?.objectLocation
                        )
                    }

                    // Birth Date
                    if (!animal?.birthDate.isNullOrBlank()) {
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = animal?.birthDate!!,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Action Buttons Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ActionItem(
                            icon = R.drawable.feed,
                            label = "Feeding",
                            value = animal?.lastFeeding ?: "N/A",
                            onClick = { pendingAction = PendingAction.FEED }
                        )
                        ActionItem(
                            icon = R.drawable.water,
                            label = "Spray",
                            value = animal?.lastSpray ?: "N/A",
                            onClick = { pendingAction = PendingAction.SPRAY }
                        )

                        if (animal?.sizeType == 1L) {
                            val moltDate = animal?.lastMolt ?: "N/A"
                            val moltStage = if (animal?.size != null) "L${animal?.size}" else "N/A"
                            ActionItem(
                                icon = R.drawable.molt,
                                label = "Molt",
                                value = "$moltDate ($moltStage)",
                                onClick = { pendingAction = PendingAction.MOLT }
                            )
                        } else {
                            val unit = if (animal?.sizeType == 0L) "cm" else "other"
                            val sizeValue = if (animal?.size != null) "${animal?.size} $unit" else "N/A"
                            ActionItem(
                                icon = R.drawable.molt,
                                label = "Size",
                                value = sizeValue,
                                onClick = {
                                    newSizeText = animal?.size?.toString() ?: ""
                                    showSizeDialog = true
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Notes
                    if (!animal?.notes.isNullOrBlank()) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Notes: ${animal?.notes}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }

        // Action Confirmation Dialog
        ActionConfirmationDialog(
            pendingAction = pendingAction,
            onDismiss = { pendingAction = PendingAction.NONE },
            onConfirm = {
                when (pendingAction) {
                    PendingAction.FEED -> viewModel.setLastFeeding(animalId)
                    PendingAction.SPRAY -> viewModel.setLastSpray(animalId)
                    PendingAction.MOLT -> viewModel.setLastMolt(animalId)
                    else -> {}
                }
                pendingAction = PendingAction.NONE
            }
        )
        
        // Size Update Dialog
        if (showSizeDialog) {
            UpdateSizeDialog(
                initialSize = newSizeText,
                onDismiss = { showSizeDialog = false },
                onConfirm = { newSize ->
                    viewModel.setSize(animalId, newSize)
                    showSizeDialog = false
                }
            )
        }
    }
}
