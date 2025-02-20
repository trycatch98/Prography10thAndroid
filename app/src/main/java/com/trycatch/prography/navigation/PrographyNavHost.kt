package com.trycatch.prography.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.trycatch.prography.presentation.detail.DetailRoute
import com.trycatch.prography.presentation.main.MainRoute
import com.trycatch.prography.presentation.photo.PhotoRoute

@Composable
fun PrographyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MainRoute
    ) {
        composable<MainRoute> {
            MainRoute { id ->
                navController.navigate(DetailRoute(id))
            }
        }

        composable<PhotoRoute> {
            PhotoRoute()
        }

        dialog<DetailRoute>(
            dialogProperties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            DetailRoute()
        }
    }
}