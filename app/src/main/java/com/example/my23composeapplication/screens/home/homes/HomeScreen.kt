package com.example.my23composeapplication.screens.home.homes

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.my23composeapplication.model.Movie
import com.example.my23composeapplication.model.getMovies
import com.example.my23composeapplication.navigation.MovieScreens
import com.example.my23composeapplication.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Color.Magenta
            ),
            title = { Text(text = "Movies") },
            scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

        )
    },
        bottomBar = {
            BottomAppBar(

                containerColor = Color.Yellow
            )
            { Text(text = "Bottom") }
        },
        contentColor = Color.Black,

        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .wrapContentSize()
            ) {
                MainContent(navController =navController)
                Text(text = "${innerPadding.toString()}")
            }


        })
}

@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies()
        /*listOf(
        "Spit on your Grave",
        "Inside",
        "Psycho",
        "Dracula",
        "Evil Dead",
        "Halloween",
        "The Burbs"*/

) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = movieList) {

                MovieRow(movie = it){ movie ->
                       navController.navigate(route = MovieScreens.DetailsScreen.name+"/$movie")
                }
            }
        }
    }
}


