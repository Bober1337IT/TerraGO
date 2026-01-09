package com.terrago.app.ui.screens.animals.components.topbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.terrago.app.ui.theme.TerraGOTheme

@Composable
fun TopActionIconWithLabel(
    drawable: Int,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    TerraGOTheme(dynamicColor = false) {
        val backgroundColor by animateColorAsState(
            if (selected) MaterialTheme.colorScheme.primary else Color.Transparent,
            label = "backgroundColor"
        )
        val contentColor by animateColorAsState(
            if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
            label = "contentColor"
        )
        val iconPadding by animateDpAsState(
            if (selected) 8.dp else 10.dp,
            label = "iconPadding"
        )

        Column(
            modifier = Modifier
                .width(72.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable { onClick() }
                .padding(vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(backgroundColor)
                    .padding(iconPadding),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = drawable),
                    contentDescription = label,
                    modifier = Modifier.fillMaxSize(),
                    colorFilter = ColorFilter.tint(contentColor)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                fontSize = 10.sp,
                maxLines = 1
            )
        }
    }
}