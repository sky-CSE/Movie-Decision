package com.example.movie_decision.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movie_decision.model.Movie
import com.example.movie_decision.model.getMovies

@ExperimentalAnimationApi
@Preview
@Composable
fun MovieRow(
    movie: Movie = getMovies()[0],
    onItemClick: (String) -> Unit = {}
) {
    //PLOT DETAILS EXPANDED STATE
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp, top = 6.dp, bottom = 6.dp)
            .fillMaxWidth()
            //.height(130.dp) //to expand the card we made it dynamic
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 4.dp,
        backgroundColor = Color(0xFFECEFF1)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            //COIL
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.images[2])
                    .crossfade(true)
                    .build(),
                contentDescription = "Image Poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(6.dp)
                    .size(96.dp)
                    .clip(CircleShape),
            )

//                Icon(imageVector = Icons.Default.AccountBox,
//                    contentDescription = "Movie Image")

            Column(modifier = Modifier.padding(8.dp)) {

                //Text buildAnnotatedString is used to manipulate each word individually
                Text(buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    ) {
                        append(movie.title)
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.DarkGray,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append(" (${movie.rating})")
                    }
                }, maxLines = 2)

                Text(text = movie.genre, maxLines = 1, style = MaterialTheme.typography.caption)
                Text(text = movie.year, style = MaterialTheme.typography.caption)

                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else
                        Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down Arrow",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded = !expanded
                        },
                    tint = Color.DarkGray
                )

                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 14.sp
                                )
                            ) {
                                append("Plot: ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Light
                                )
                            ) {
                                append(movie.plot)
                            }
                        }, modifier = Modifier.padding(6.dp))

                        Divider(thickness = 1.dp)
                        Text(text = "Director: ${movie.director}", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.caption)
                        Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.caption)
                    }
                }
            }
        }
    }
}