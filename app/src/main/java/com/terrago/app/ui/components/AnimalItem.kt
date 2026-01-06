package com.terrago.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.terrago.app.database.entity.AnimalPreview
import com.terrago.app.ui.components.photo.PhotoFromByteArray
import com.terrago.app.ui.theme.TerraGOTheme

@Composable
fun AnimalItem(
    animal: AnimalPreview,
    onClick: (Long) -> Unit
) {
    TerraGOTheme(dynamicColor = false) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .clickable { onClick(animal.animalId) },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left Side: Text Information
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = animal.speciesLatinName ?: "Unknown species",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 18.sp,
                        letterSpacing = 0.5.sp
                    )
                    
                    if (!animal.animalName.isNullOrBlank()) {
                        Text(
                            text = animal.animalName,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Text(
                        text = animal.objectName ?: "No Habitat",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Dynamic Status Lines
                    StatusLine(label = "Last fed", value = animal.lastFeeding)
                    StatusLine(label = "Last sprayed", value = animal.lastSpray)
                    
                    if (animal.sizeType == 1L) {
                        // Case: Molt Stage (L7, L8 etc.)
                        val moltDate = animal.lastMolt ?: "N/A"
                        val moltStage = if (animal.size != null) "L${animal.size}" else "N/A"
                        StatusLine(label = "Last molt", value = "$moltDate ($moltStage)")
                    } else {
                        // Case: cm or other unit
                        val unit = if (animal.sizeType == 0L) "cm" else "other"
                        val sizeValue = if (animal.size != null) "${animal.size} $unit" else "N/A"
                        
                        StatusLine(label = "Size", value = sizeValue)
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Right Side: Image
                PhotoFromByteArray(
                    bytes = animal.photo,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }
        }
    }
}

@Composable
private fun StatusLine(label: String, value: String?) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 1.dp)
    ) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = value ?: "N/A",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold
        )
    }
}
