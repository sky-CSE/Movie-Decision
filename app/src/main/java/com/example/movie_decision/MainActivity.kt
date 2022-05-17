package com.example.movie_decision

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.movie_decision.navigation.MovieNavigation
import com.example.movie_decision.ui.theme.MovieDecisionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieDecisionTheme {
                MyApp {
                    MovieNavigation()
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MovieDecisionTheme {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieDecisionTheme {
        MovieNavigation()
    }
}