package com.terrago.app.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.terrago.app.viewmodel.AnimalsViewModel

@Composable
fun AnimalsScreen(
    viewModel: AnimalsViewModel,
    onAnimalClick: (Long) -> Unit
) {
    val animals by viewModel.animalsPreview.collectAsState()

    LazyColumn {
        items(animals) { animal ->
            //TODO
        }
    }
}