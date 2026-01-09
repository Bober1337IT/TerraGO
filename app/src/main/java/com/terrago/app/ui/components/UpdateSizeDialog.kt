package com.terrago.app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun UpdateSizeDialog(
    initialSize: String,
    onDismiss: () -> Unit,
    onConfirm: (Long) -> Unit
) {
    var sizeText by remember { mutableStateOf(initialSize) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Update Size") },
        text = {
            OutlinedTextField(
                value = sizeText,
                onValueChange = { sizeText = it },
                label = { Text("New size") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            TextButton(onClick = {
                sizeText.toLongOrNull()?.let {
                    onConfirm(it)
                }
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
