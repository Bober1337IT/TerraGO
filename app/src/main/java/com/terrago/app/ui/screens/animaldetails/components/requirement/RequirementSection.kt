package com.terrago.app.ui.screens.animaldetails.components.requirement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RequirementSection() {
    // Placeholder for future implementation
    Column {
        val hasRequirements = false
        if (hasRequirements) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RequirementItem("Temperature", "20-26°C")
                RequirementItem("Humidity", "60-70%")
                RequirementItem("Light Cycle", "12 h")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        val hasDescription = false
        if (hasDescription) {
            Text(
                text = "Description: //TODO species description",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}