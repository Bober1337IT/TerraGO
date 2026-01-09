package com.terrago.app.ui.screens.animaldetails.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SpeciesExpandableInfo(
    latinName: String,
    commonName: String?,
    description: String?,
    tempMin: Double?,
    tempMax: Double?,
    humMin: Double?,
    humMax: Double?,
    lightCycle: Long?
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { expanded = !expanded }
            .padding(8.dp)
    ) {
        Text(
            text = latinName,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )

        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                if (!commonName.isNullOrBlank()) {
                    Text(
                        text = commonName,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    if (tempMin != null || tempMax != null) {
                        RequirementItem(
                            label = "Temp",
                            value = "${tempMin ?: "?"}-${tempMax ?: "?"}°C"
                        )
                    }
                    if (humMin != null || humMax != null) {
                        RequirementItem(
                            label = "Humidity",
                            value = "${humMin ?: "?"}-${humMax ?: "?"}%"
                        )
                    }
                    if (lightCycle != null) {
                        RequirementItem(
                            label = "Light",
                            value = "${lightCycle}h"
                        )
                    }
                }

                if (!description.isNullOrBlank()) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}
