package com.terrago.app.ui.screens.animals.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terrago.app.database.entity.AnimalPreview
import com.terrago.app.db.Animals

@Composable
fun AnimalItem(
    animal: AnimalPreview,
    onClick: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { onClick(animal.animalId) }
            .padding(16.dp)
    ) {
        Text(text = animal.animalName ?: "No Name")
        Text(text = animal.speciesLatinName ?: "")
        Text(text = "Terrarium: ${animal.objectName}")
        Text(text = "Last feeding: ${animal.lastFeeding}")
    }
}

// For testing purposes
@Composable
fun AnimalItemTest(
    animal: Animals,
    onClick: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .clickable { onClick(animal.animal_id) }
    ) {
        Text("ID: ${animal.animal_id}")
        Text("Name: ${animal.name ?: "-"}")
        Text("Gender: ${animal.gender ?: "-"}")
        Text("Size: ${animal.size ?: "-"}")
    }
}