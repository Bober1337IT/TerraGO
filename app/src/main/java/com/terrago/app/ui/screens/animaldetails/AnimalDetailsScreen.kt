package com.terrago.app.ui.screens.animaldetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.terrago.app.ui.components.photo.PhotoFromByteArray
import com.terrago.app.viewmodel.animalsviewmodel.AnimalsViewModel

@Composable
fun AnimalDetailsScreen(
    viewModel: AnimalsViewModel,
    animalId: Long,
    onEditClick: (Long) -> Unit
) {
    val animal by viewModel.getAnimalDetails(animalId).collectAsState(initial = null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Photo
        PhotoFromByteArray(
            bytes = animal?.photo,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Basic Info
        Text(text = "Name: ${animal?.animalName ?: "Unnamed"}", fontWeight = FontWeight.Bold)
        Text(text = "Species: ${animal?.speciesLatinName ?: "Unknown"}")
        Text(text = "Habitat: ${animal?.objectName ?: "None"}")
        Text(text = "Gender: ${animal?.gender ?: "Not specified"}")
        Text(text = "Birth Date: ${animal?.birthDate ?: "Unknown"}")

        // Size logic
        val unit = when (animal?.sizeType) {
            0L -> "cm"
            1L -> "molt"
            else -> ""
        }
        Text(text = "Size: ${animal?.size ?: "Unknown"} $unit")

        // Log
        Text(text = "Last Feeding: ${animal?.lastFeeding ?: "No data"}")
        Text(text = "Last Spray: ${animal?.lastSpray ?: "No data"}")
        Text(text = "Last Molt: ${animal?.lastMolt ?: "No data"}")

        // Notes
        Text(text = "Notes:", fontWeight = FontWeight.Bold)
        Text(text = animal?.notes ?: "No notes.")

        // Edit Button
        Button(
            onClick = { onEditClick(animalId) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Edit Animal")
        }
        Spacer(modifier = Modifier.height(64.dp))
    }
}
