package com.terrago.app.ui.screens.animaldetails.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.terrago.app.ui.components.enumclasses.PendingAction

@Composable
fun ActionConfirmationDialog(
    pendingAction: PendingAction,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (pendingAction == PendingAction.NONE) return

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Confirmation",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        text = {
            val actionName = when (pendingAction) {
                PendingAction.FEED -> "Feeding"
                PendingAction.SPRAY -> "Spray"
                PendingAction.MOLT -> "Molt"
                else -> ""
            }
            Text(
                text = "Are you sure you want to update the last $actionName date to today?",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text("Confirm", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = MaterialTheme.colorScheme.primary)
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(28.dp)
    )
}
