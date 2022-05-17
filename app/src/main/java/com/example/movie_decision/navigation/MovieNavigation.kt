package com.example.movie_decision.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movie_decision.screens.DetailsScreen
import com.example.movie_decision.screens.HomeScreen

@Composable
fun MovieNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name
    ) {
        //only composable can be stored in NavGraph
        composable(MovieScreens.HomeScreen.name) {
            //here we pass where this should lead us to
            HomeScreen(navController = navController)
        }

        composable(MovieScreens.DetailScreen.name) {
            DetailsScreen(navController = navController)
        }
    }
}