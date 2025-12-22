package com.terrago.app.ui.screens.animals

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.terrago.app.ui.screens.animals.components.AnimalItem
import com.terrago.app.viewmodel.animalsviewmodel.AnimalsViewModel

@Composable
fun AnimalsScreen(
    viewModel: AnimalsViewModel,
    onAnimalClick: (Long) -> Unit,
    onAddAnimalClick: () -> Unit
) {
    val animals by viewModel.animalsPreview.collectAsState()

    Column{
        Text("Animals count: ${animals.size}")
        animals.forEach {
            println("ANIMAL: ${it.animalName}")
        }
        Button(onClick = onAddAnimalClick) {
            Text("Add Animal")
        }

        LazyColumn(
            modifier = Modifier.weight(1f)
        ){
            items(animals) { animal ->
                AnimalItem(animal, onAnimalClick)
            }
        }
    }
}