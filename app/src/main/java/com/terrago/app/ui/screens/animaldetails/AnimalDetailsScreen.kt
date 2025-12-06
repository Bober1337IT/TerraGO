package com.terrago.app.ui.screens.animaldetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.terrago.app.viewmodel.animalsviewmodel.AnimalsViewModel

@Composable
fun AnimalDetailsScreen(
    viewModel: AnimalsViewModel,
    animalId: Long
) {
    val animal by viewModel.getAnimalDetails(animalId).collectAsState(initial = null)
}