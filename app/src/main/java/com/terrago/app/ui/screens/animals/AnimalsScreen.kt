package com.terrago.app.ui.screens.animals

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.terrago.app.ui.screens.animals.components.AnimalItem
import com.terrago.app.ui.screens.animals.mock.MockAnimals
import com.terrago.app.viewmodel.AnimalsViewModel

@Composable
fun AnimalsScreen(
    viewModel: AnimalsViewModel,
    onAnimalClick: (Long) -> Unit
) {
    //val animals by viewModel.animalsPreview.collectAsState()
    val animals = MockAnimals.sampleAnimals // Temporary

    LazyColumn {
        items(animals) { animal ->
            AnimalItem(animal, onAnimalClick)
        }
    }
}