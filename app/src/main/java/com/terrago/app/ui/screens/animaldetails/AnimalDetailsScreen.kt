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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.terrago.app.R
import com.terrago.app.ui.theme.TerraGOTheme
import com.terrago.app.ui.components.photo.PhotoFromByteArray
import com.terrago.app.viewmodel.animalsviewmodel.AnimalsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalDetailsScreen(
    viewModel: AnimalsViewModel,
    animalId: Long,
    onBack: () -> Unit,
    onEditClick: (Long) -> Unit
) {
    val animal by viewModel.getAnimalDetails(animalId).collectAsState(initial = null)

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
                    // Photo - Only show if photo is not null
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

                    // Species Name (Primary Info)
                    Text(
                        text = animal?.speciesLatinName ?: "Unknown Species",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    // Gender - Only show if present
                    if (!animal?.gender.isNullOrBlank()) {
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = if (animal?.gender == "Female") "♀" else "♂",
                            fontSize = 28.sp,
                            color = if (animal?.gender == "Female") Color(0xFFFF69B4) else Color(0xFF2196F3)
                        )
                    }

                    // Size/Molt Stage - Only show if present
                    if (animal?.size != null) {
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = if (animal?.sizeType == 1L) "L${animal?.size}" else "${animal?.size} ${if (animal?.sizeType == 0L) "cm" else "other"}",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Nickname - Only show if present
                    if (!animal?.animalName.isNullOrBlank()) {
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = animal?.animalName!!,
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }

                    // Habitat Details - Only show if present
                    if (!animal?.objectName.isNullOrBlank()) {
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "${animal?.objectName}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }

                    // Birth Date - Only show if present
                    if (!animal?.birthDate.isNullOrBlank()) {
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = animal?.birthDate!!,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Actions Row - Icons are usually always visible as they represent actions
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ActionItem(
                            icon = R.drawable.feed,
                            label = "Feeding",
                            daysAgo = animal?.lastFeeding ?: "-"
                        )
                        ActionItem(
                            icon = R.drawable.water,
                            label = "Spray",
                            daysAgo = animal?.lastSpray ?: "-"
                        )

                        if (animal?.sizeType == 1L) {
                            val moltDate = animal?.lastMolt ?: "-"
                            val moltStage = if (animal?.size != null) "L${animal?.size}" else "-"
                            ActionItem(
                                icon = R.drawable.molt,
                                label = "Molt",
                                daysAgo = "$moltDate ($moltStage)"
                            )
                        } else {
                            val unit = if (animal?.sizeType == 0L) "cm" else "other"
                            val sizeValue = if (animal?.size != null) "${animal?.size} $unit" else "-"
                            ActionItem(
                                icon = R.drawable.molt,
                                label = "Size",
                                daysAgo = sizeValue
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Notes - Only show if present
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

                    // Species Requirements - //TODO: Fetch these from Species table
                    // Wrapped in if check for future implementation
                    val hasRequirements = false // TODO: Change when data available
                    if (hasRequirements) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            RequirementItem("Temperature", "20-26°C") // TODO
                            RequirementItem("Humidity", "60-70%") // TODO
                            RequirementItem("Light Cycle", "12 h") // TODO
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Description - //TODO: Fetch from Species table
                    // Wrapped in if check for future implementation
                    val hasDescription = false // TODO: Change when data available
                    if (hasDescription) {
                        Text(
                            text = "Description: //TODO species description",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ActionItem(icon: Int, label: String, daysAgo: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Text(text = label, style = MaterialTheme.typography.labelMedium)
        Text(text = daysAgo, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.outline)
    }
}

@Composable
fun RequirementItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.outline)
        Text(text = value, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
    }
}
