package com.terrago.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TerrariumCard(
    name: String,
    width: Long?,
    length: Long?,
    height: Long?,
    description: String?,
    location_name: String?,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
            .background(Color.White, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(12.dp)

    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = "${width ?: "?"} x ${length ?: "?"} x ${height ?: "?"} cm",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(Modifier.height(4.dp))

        if (!location_name.isNullOrBlank()) {
            Text(
                text = location_name,
                style = MaterialTheme.typography.bodySmall
            )
        }
        if (!description.isNullOrBlank()) {
            Spacer(Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}