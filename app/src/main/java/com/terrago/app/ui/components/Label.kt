package com.terrago.app.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Label(text: String) {
    Text(text, style = MaterialTheme.typography.bodyMedium)
}