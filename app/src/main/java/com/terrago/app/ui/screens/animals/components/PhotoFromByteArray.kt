package com.terrago.app.ui.screens.animals.components

import androidx.compose.ui.graphics.asImageBitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp

@Composable
fun PhotoFromByteArray(bytes: ByteArray?, modifier: Modifier) {
    if (bytes == null || bytes.isEmpty()) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Text("No Img", fontSize = 10.sp)
        }
    } else {
        val bitmap = remember(bytes) {
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        }

        if (bitmap != null) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = modifier,
                contentScale = ContentScale.Crop
            )
        }
    }
}
