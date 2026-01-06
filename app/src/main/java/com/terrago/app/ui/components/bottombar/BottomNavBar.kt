package com.terrago.app.ui.components.bottombar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.terrago.app.R

enum class BottomNavItem {
    LEFT,
    RIGHT
}
@Composable
fun BottomNavBar(
    selected: BottomNavItem,
    onItemSelected: (BottomNavItem) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(Color(0xFF1B5E20)),
    ) {
        Spacer(modifier = Modifier.weight(1f))
        BottomNavItemView(
            drawable = R.drawable.water,
            selected = selected == BottomNavItem.LEFT,
            onClick = { onItemSelected(BottomNavItem.LEFT) }
        )
        Spacer(modifier = Modifier.weight(1f))
        BottomNavItemView(
            drawable = R.drawable.feed,
            selected = selected == BottomNavItem.RIGHT,
            onClick = { onItemSelected(BottomNavItem.RIGHT) }
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun BottomNavItemView(
    drawable: Int,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(72.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        if (selected) {
            Box(
                modifier = Modifier
                    .height(4.dp)
                    .width(28.dp)
                    .background(Color.White, shape = CircleShape)
            )
        } else {
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
