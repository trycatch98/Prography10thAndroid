package com.trycatch.prography

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.trycatch.prography.navigation.PrographyNavHost
import com.trycatch.prography.navigation.TopLevelDestination
import com.trycatch.prography.ui.components.PrographyNavigationBar
import com.trycatch.prography.ui.components.PrographyNavigationBarItem

@Composable
fun PrographyApp() {
    val navController = rememberNavController()

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries
    val currentDestination: NavDestination? = navController
        .currentBackStackEntryAsState().value?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            PrographyNavHost(
                navController = navController,
            )

            if (topLevelDestinations.any { currentDestination?.route?.contains(it.route.toString()) == true }) {
                PrographyNavigationBar(
                    modifier = Modifier.align(Alignment.BottomCenter)
                ) {
                    topLevelDestinations.forEach { destination ->
                        val selected =
                            currentDestination?.route?.contains(destination.route.toString()) ?: false
                        PrographyNavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(destination.route) {
                                    navController.graph.startDestinationRoute?.let {
                                        popUpTo(it) { saveState = true }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = destination.icon,
                        )
                    }
                }
            }
        }
    }
}