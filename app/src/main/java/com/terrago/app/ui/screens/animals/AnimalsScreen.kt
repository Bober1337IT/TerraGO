package com.terrago.app.ui.screens.animals
import android.app.Activity
import com.terrago.app.R
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsControllerCompat
import com.terrago.app.database.entity.AnimalPreview
import com.terrago.app.ui.components.AnimalItem
import com.terrago.app.viewmodel.animalsviewmodel.AnimalsViewModel
import com.terrago.app.ui.components.topbar.TopActionsBar
import com.terrago.app.ui.components.topbar.TopActionsBarWithIcons

enum class ListAction {
    NAVIGATE, FEED, SPRAY, MOLT
}

@Composable
fun AnimalsScreen(
    viewModel: AnimalsViewModel,
    onAnimalClick: (Long) -> Unit,
    onAddAnimalClick: () -> Unit
) {
//    val view = LocalView.current
//    SideEffect {
//        val window = (view.context as Activity).window
//        window.statusBarColor = Color(0x00000000).toArgb()
//        WindowInsetsControllerCompat(window, view)
//            .isAppearanceLightStatusBars = true
//    }
    val animals by viewModel.animalsPreview.collectAsState()
    var currentAction by remember { mutableStateOf(ListAction.NAVIGATE) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddAnimalClick,
                containerColor = Color(0xFF2E7D32),
                contentColor = Color.White,
                modifier = Modifier.padding(bottom=48.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add animal")
            }
        },
        containerColor = Color(0xFFEFFFEF)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            TopActionsBarWithIcons(
                currentAction = currentAction,
                onActionSelected = { action ->
                    currentAction =
                        if (currentAction == action) ListAction.NAVIGATE else action
                }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = animals,
                    key = { it.animalId }
                ) { animal ->
                    AnimalItem(
                        animal = animal,
                        onClick = { id ->
                            when (currentAction) {
                                ListAction.NAVIGATE -> onAnimalClick(id)
                                ListAction.FEED -> {
                                    viewModel.setLastFeeding(id)
                                    currentAction = ListAction.NAVIGATE
                                }
                                ListAction.SPRAY -> {
                                    viewModel.setLastSpray(id)
                                    currentAction = ListAction.NAVIGATE
                                }
                                ListAction.MOLT -> {
                                   // viewModel.setLastMolt(id)
                                    currentAction = ListAction.NAVIGATE
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}


