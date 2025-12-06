package com.terrago.app.ui.screens.animalform

import androidx.compose.runtime.Composable
import com.terrago.app.viewmodel.animalsviewmodel.AnimalsViewModel

@Composable
fun AnimalFormScreen(
    animalId: Long?,                 // null = ADD, number = EDIT
    viewModel: AnimalsViewModel,
    onBack: () -> Unit
) {
}