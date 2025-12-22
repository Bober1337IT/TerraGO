package com.terrago.app.ui.screens.animaldetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terrago.app.viewmodel.animalsviewmodel.AnimalsViewModel

@Composable
fun AnimalDetailsScreen(
    viewModel: AnimalsViewModel,
    animalId: Long,
    onEditClick: (Long) -> Unit // To Edit
) {
    val animal by viewModel.getAnimalDetails(animalId).collectAsState(initial = null)

    Column(Modifier.padding(16.dp)) {
        animal?.animalName?.let {
            Text(text = it)
        }

        Spacer(Modifier.height(16.dp))

        Button(onClick = { onEditClick(animalId) }) {
            Text("Edit Animal")
        }
    }
}