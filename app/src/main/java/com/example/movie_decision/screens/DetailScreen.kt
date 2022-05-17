package com.example.movie_decision.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.movie_decision.model.Movie
import com.example.movie_decision.model.getMovies


@Composable
fun DetailsScreen(
    navController: NavController,
    movieId: String?
) {

    val newMovieList = getMovies().filter { movie -> movie.id == movieId }

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color(0xFFFF0266),
            elevation = 0.dp
        ) {
            Row(horizontalArrangement = Arrangement.Start) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Button",
                    modifier = Modifier.clickable {
                        //going back to prev screen by popping out current screen
                        navController.popBackStack()
                    })

                Text(text = "Details", modifier = Modifier.padding(start = 20.dp))
            }

        }
    }) {

        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = newMovieList.first().title.toString(),
                    style = MaterialTheme.typography.h5
                )
                VerticalScrollableImageView(newMovieList)
            }
        }
    }
}

@Composable
private fun VerticalScrollableImageView(newMovieList: List<Movie>) {
    LazyColumn {
        items(newMovieList.first().images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .height(240.dp), elevation = 5.dp
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = image),
                    contentDescription = "Movie Image",
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

