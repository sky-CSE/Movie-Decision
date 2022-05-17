package com.example.movie_decision.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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

        //www.google.com/detail-screen/id=34
        //+"/{movie}" is the argument
        composable(MovieScreens.DetailScreen.name +"/{movie}",
            arguments = listOf(navArgument(name = "movie") {type = NavType.StringType})) {
                backStackEntry ->

            DetailsScreen(navController = navController,
                backStackEntry.arguments?.getString("movie"))
        }
    }
}