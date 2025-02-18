package com.trycatch.prography.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.trycatch.prography.ui.presentation.main.MainRoute
import com.trycatch.prography.ui.presentation.photo.PhotoRoute

@Composable
fun PrographyNavHost(
    navController: NavHostController
) {
    NavHost(
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