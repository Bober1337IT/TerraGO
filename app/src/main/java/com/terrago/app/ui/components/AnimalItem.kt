package com.terrago.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.terrago.app.database.entity.AnimalPreview
import com.terrago.app.ui.components.photo.PhotoFromByteArray

@Composable
fun AnimalItem(
    animal: AnimalPreview,
    onClick: (Long) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(animal.animalId) }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left Side: Text Information
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "${animal.animalName ?: "Unnamed"} (${animal.speciesLatinName ?: "Unknown"})",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Habitat: ${animal.objectName ?: "None"}",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Readable Logs
            Text(
                text = "Fed: ${animal.lastFeeding ?: "No data"}",
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "Spray: ${animal.lastSpray ?: "No data"}",
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "Molt: ${animal.lastMolt ?: "No data"}",
                style = MaterialTheme.typography.labelSmall
            )
        }

        // Right Side: Image
        PhotoFromByteArray(
            bytes = animal.photo,
            modifier = Modifier
                .size(100.dp)
                .padding(start = 16.dp)
        )
    }
}


