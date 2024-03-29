package com.example.my23composeapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.my23composeapplication.chapters.MovieApp
import com.example.my23composeapplication.navigation.MovieNavigation
import com.example.my23composeapplication.ui.theme.My23ComposeApplicationTheme

class MainActivity : ComponentActivity() {

val movieApp = MovieApp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
              MovieNavigation()
            }
        }
    }


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun MyApp(content: @Composable () -> Unit) {

        My23ComposeApplicationTheme {
                content()

        }
    }

        //
        @Preview
        @Composable
        fun DefaultPreview() {
            My23ComposeApplicationTheme {

                MyApp {
                    MovieNavigation()
                    //var firstClick = FirstClick()
                    // firstClick.MyFirstClickApp()
                }
            }
        }



    }








