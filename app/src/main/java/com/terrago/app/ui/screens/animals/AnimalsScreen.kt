package com.terrago.app.ui.screens.animals

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terrago.app.ui.components.AnimalItem
import com.terrago.app.viewmodel.animalsviewmodel.AnimalsViewModel

enum class ListAction {
    NAVIGATE, FEED, SPRAY
}

@Composable
fun AnimalsScreen(
    viewModel: AnimalsViewModel,
    onAnimalClick: (Long) -> Unit,
    onAddAnimalClick: () -> Unit
) {
    val animals by viewModel.animalsPreview.collectAsState()

    // State to track what happens when an item is clicked
    var currentAction by remember { mutableStateOf(ListAction.NAVIGATE) }

    Column {
        // Top Action Bar
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Button(
                onClick = { currentAction = if (currentAction == ListAction.FEED) ListAction.NAVIGATE else ListAction.FEED },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                colors = if (currentAction == ListAction.FEED) ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary) else ButtonDefaults.buttonColors()
            ) {
                Text(if (currentAction == ListAction.FEED) "Select to Feed" else "Feed")
            }

            Button(
                onClick = { currentAction = if (currentAction == ListAction.SPRAY) ListAction.NAVIGATE else ListAction.SPRAY },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                colors = if (currentAction == ListAction.SPRAY) ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary) else ButtonDefaults.buttonColors()
            ) {
                Text(if (currentAction == ListAction.SPRAY) "Select to Spray" else "Spray")
            }
        }

        Text(
            text = "Animals count: ${animals.size}",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Button(
            onClick = onAddAnimalClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Add Animal")
        }

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(animals) { animal ->
                AnimalItem(
                    animal = animal,
                    onClick = { id ->
                        when (currentAction) {
                            ListAction.NAVIGATE -> onAnimalClick(id)
                            ListAction.FEED -> {
                                viewModel.setLastFeeding(id)
                                currentAction = ListAction.NAVIGATE // Reset to navigation after action
                            }
                            ListAction.SPRAY -> {
                                viewModel.setLastSpray(id)
                                currentAction = ListAction.NAVIGATE // Reset to navigation after action
                            }
                        }
                    }
                )
            }
        }
    }
}