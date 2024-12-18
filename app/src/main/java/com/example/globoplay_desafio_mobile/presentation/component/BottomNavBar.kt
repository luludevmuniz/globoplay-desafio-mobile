package com.example.globoplay_desafio_mobile.presentation.component

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.globoplay_desafio_mobile.navigation.destinations.NavBarDestionation

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    currentNavBarDestionation: NavBarDestionation,
    onDestinationChanged: (NavBarDestionation) -> Unit
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        NavBarDestionation.entries.forEach { destination ->
            NavigationBarItem(
                modifier = Modifier.testTag(destination.testTag),
                selected = destination == currentNavBarDestionation,
                onClick = { onDestinationChanged(destination) },
                icon = {
                    Icon(
                        painter = painterResource(destination.icon),
                        contentDescription = stringResource(destination.contentDescription)
                    )
                },
                label = {
                    Text(
                        text = stringResource(destination.label)
                    )
                }
            )
        }
    }
}