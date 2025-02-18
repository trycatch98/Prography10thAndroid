package com.trycatch.prography.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.trycatch.prography.ui.presentation.main.MainRoute
import com.trycatch.prography.ui.presentation.photo.PhotoRoute

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
            MainRoute()
        }

        composable<PhotoRoute> {
            PhotoRoute()
        }
    }
}