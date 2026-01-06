package com.terrago.app.ui.components.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.terrago.app.R
import com.terrago.app.ui.screens.animals.ListAction
import com.terrago.app.ui.theme.TerraGOTheme

@Composable
fun TopActionsBarWithIcons(
    currentAction: ListAction,
    onActionSelected: (ListAction) -> Unit
) {
    TerraGOTheme(dynamicColor = false) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App logo",
                modifier = Modifier.height(48.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TopActionIconWithLabel(
                    drawable = R.drawable.water,
                    label = "Spray",
                    selected = currentAction == ListAction.SPRAY,
                    onClick = { onActionSelected(ListAction.SPRAY) }
                )

                TopActionIconWithLabel(
                    drawable = R.drawable.feed,
                    label = "Feeding",
                    selected = currentAction == ListAction.FEED,
                    onClick = { onActionSelected(ListAction.FEED) }
                )

                TopActionIconWithLabel(
                    drawable = R.drawable.molt,
                    label = "Molt",
                    selected = currentAction == ListAction.MOLT,
                    onClick = { onActionSelected(ListAction.MOLT) }
                )
            }
        }
    }
}
