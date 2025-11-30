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
import com.terrago.app.ui.screens.animals.components.AnimalItemTest
import com.terrago.app.ui.screens.animals.mock.MockAnimals
import com.terrago.app.viewmodel.AnimalsViewModel

@Composable
fun AnimalsScreen(
    viewModel: AnimalsViewModel,
    onAnimalClick: (Long) -> Unit
) {
    //val animals by viewModel.animalsPreview.collectAsState()
    val animals by viewModel.getAllAnimals.collectAsState()
    //val animals = MockAnimals.sampleAnimals // Temporary

    Column{
        Text("Animals count: ${animals.size}")
        animals.forEach {
            println("ANIMAL: ${it.name}")
        }

        LazyColumn(
            modifier = Modifier.weight(1f)
        ){
            items(animals) { animal ->
                AnimalItemTest(animal, onAnimalClick)
            }
        }
        Button(onClick = {
            println("INSERT BEGIN")
            viewModel.insertAnimal(
                objectId = 1,
                speciesId = 3,
                name = "Brachypelma",
                gender = "F",
                birthDate = "2023-11-01",
                lastFeeding = null,
                lastSpray = null,
                lastMolt = null,
                size = 5,
                sizeType = 1,
                notes = null,
                photo = null
            )
            println("INSERT REQUEST SENT")
        }) {
            Text("Add Animal")
        }
        Button(
            onClick = {
                viewModel.deleteAllAnimals()
                println("DELETED ALL ANIMALS")
            }
        ) {
            Text("Delete All Animals")
        }
    }
}