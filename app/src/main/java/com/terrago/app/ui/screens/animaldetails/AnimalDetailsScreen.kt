package com.terrago.app.ui.screens.animaldetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.terrago.app.viewmodel.AnimalsViewModel

@Composable
fun AnimalDetailsScreen(
    viewModel: AnimalsViewModel,
    animalId: Long
) {
    //TESTING PURPOSES ONLY
    val animal = viewModel.getAllAnimals.collectAsState().value.firstOrNull { it.animal_id == animalId }
    Text(text = "Name: ${animal?.name ?: "-"}")
    //TODO
}