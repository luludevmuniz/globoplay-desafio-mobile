package com.example.globoplay_desafio_mobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.globoplay_desafio_mobile.navigation.destinations.Highlights
import com.example.globoplay_desafio_mobile.navigation.destinations.Principal
import com.example.globoplay_desafio_mobile.presentation.highlights.HighlightsScreen
import com.example.globoplay_desafio_mobile.presentation.principal.PrincipalScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Principal
    ) {
        composable<Principal> {
            PrincipalScreen(
                onMediaSelected = { mediaId, mediaType ->
                    navController.navigate(
                        Highlights(
                            mediaId = mediaId,
                            mediaType = mediaType
                        )
                    )
                }
            )
        }
        composable<Highlights> {
            HighlightsScreen(
                onMediaSelected = { mediaId, mediaType ->
                    navController.navigate(
                        Highlights(
                            mediaId = mediaId,
                            mediaType = mediaType)
                    )
                },
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}