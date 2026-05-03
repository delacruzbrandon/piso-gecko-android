package com.dcbrh.pisogecko.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.dcbrh.pisogecko.presentation.details.DetailsScreen
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
            HomeScreen(
                onClickCurrency = { id ->
                    navHostController.navigate(RouteDetails(id))
                }
            )
        }
        composable<RouteDetails> { backStackEntry ->
            val details: RouteDetails = backStackEntry.toRoute()
            DetailsScreen(
                onClickClose = { navHostController.popBackStack() },
                id = details.id
            )
        }
    }
}

@Serializable
object RouteHome

@Serializable
data class RouteDetails(val id: String)