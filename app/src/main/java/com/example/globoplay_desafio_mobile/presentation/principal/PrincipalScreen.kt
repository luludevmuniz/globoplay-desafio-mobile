package com.example.globoplay_desafio_mobile.presentation.principal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import com.example.globoplay_desafio_mobile.domain.model.MediaType
import com.example.globoplay_desafio_mobile.navigation.destinations.NavBarDestionation
import com.example.globoplay_desafio_mobile.presentation.component.BottomNavBar
import com.example.globoplay_desafio_mobile.presentation.principal.home.HomeContent
import com.example.globoplay_desafio_mobile.presentation.principal.my_list.MyListContent
import com.example.globoplay_desafio_mobile.ui.theme.Padding
import com.example.globoplay_desafio_mobile.util.TestingTagsConstants.HOME_SCREEN
import com.example.globoplay_desafio_mobile.util.TestingTagsConstants.MY_LIST_SCREEN
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrincipalScreen(
    modifier: Modifier = Modifier,
    onMediaSelected: (Int, MediaType) -> Unit
) {
    var currentNavBarDestionation by rememberSaveable { mutableStateOf(NavBarDestionation.HOME) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier,
        topBar = {
            if (currentNavBarDestionation == NavBarDestionation.HOME) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "globoplay",
                            fontWeight = FontWeight.Bold
                        )
                    }
                )
            } else {
                TopAppBar(
                    title = {
                        Text(
                            text = "Minha lista",
                            fontWeight = FontWeight.Bold
                        )
                    }
                )
            }
        },
        bottomBar = {
            BottomNavBar(
                currentNavBarDestionation = currentNavBarDestionation,
                onDestinationChanged = { currentNavBarDestionation = it }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        when (currentNavBarDestionation) {
            NavBarDestionation.HOME -> HomeContent(
                modifier = Modifier
                    .verticalScroll(state = rememberScrollState())
                    .padding(paddingValues = paddingValues)
                    .padding(vertical = Padding.large)
                    .testTag(tag = HOME_SCREEN),
                onError = { error ->
                    scope.launch {
                        snackbarHostState.showSnackbar(message = error)
                    }
                },
                onMediaSelected = onMediaSelected
            )

            NavBarDestionation.MY_LIST -> {
                MyListContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.surfaceContainerHigh)
                        .padding(paddingValues = paddingValues)
                        .testTag(tag = MY_LIST_SCREEN),
                    onMediaSelected = onMediaSelected
                )
            }
        }
    }
}