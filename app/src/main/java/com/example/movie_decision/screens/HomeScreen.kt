package com.example.movie_decision.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movie_decision.model.Movie
import com.example.movie_decision.model.getMovies
import com.example.movie_decision.navigation.MovieScreens
import com.example.movie_decision.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFFFF0266),
                elevation = 6.dp
            ) {
                Text(text = "Movies", modifier = Modifier.padding(start = 20.dp))
            }
        },
    ) {
        MainContent(navController = navController)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies()){

    LazyColumn {
        items(items = movieList) {
            MovieRow(movie = it) { movie ->

                //navigating to detail screen and sending data as argument
                navController.navigate(route = MovieScreens.DetailScreen.name + "/$movie")
                //Log.d("TAG", "MainContent: $movie")
            }
        }
    }
}