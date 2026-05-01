package com.dcbrh.pisogecko.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dcbrh.pisogecko.presentation.home.HomeScreen
import kotlinx.serialization.Serializable

@Composable
fun PisoGeckoNavigation(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = RouteHome
    ) {
        composable<RouteHome> {
            HomeScreen()
        }
    }
}

@Serializable
object RouteHome