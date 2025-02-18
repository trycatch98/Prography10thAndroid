package com.trycatch.prography

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.trycatch.prography.navigation.PrographyNavHost
import com.trycatch.prography.navigation.TopLevelDestination
import com.trycatch.prography.ui.components.PrographyNavigationBar
import com.trycatch.prography.ui.components.PrographyNavigationBarItem
import com.trycatch.prography.ui.theme.PrographyTheme

@Composable
fun PrographyApp() {
    val navController = rememberNavController()

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries
    val currentDestination: NavDestination? = navController
        .currentBackStackEntryAsState().value?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 16.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "logo",
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(
                        color = PrographyTheme.colorScheme.gray30
                    ),
            )

            PrographyNavHost(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                navController = navController,
            )

            if (topLevelDestinations.any { currentDestination?.route?.contains(it.route.toString()) == true }) {
                PrographyNavigationBar {
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