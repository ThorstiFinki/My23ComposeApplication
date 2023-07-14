package com.example.my23composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.my23composeapplication.ui.theme.My23ComposeApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //var firstClick = FirstClick()
            //  var tipApp = TipApp()
            MyApp {
                // firstClick.MyFirstClickApp()
                //  tipApp.MainContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {

    My23ComposeApplicationTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    My23ComposeApplicationTheme {
        MyApp {

        //var firstClick = FirstClick()
            // firstClick.MyFirstClickApp()
          }
    }
}














