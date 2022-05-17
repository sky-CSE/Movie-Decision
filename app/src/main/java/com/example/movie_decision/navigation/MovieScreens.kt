package com.example.movie_decision.navigation

//www.google.com/sign_in
enum class MovieScreens {
    HomeScreen,
    DetailScreen;

    //companion = static i.e. no need to create instance to access it
    companion object {
        fun fromRoute(route: String?): MovieScreens = when (route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            DetailScreen.name -> DetailScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}