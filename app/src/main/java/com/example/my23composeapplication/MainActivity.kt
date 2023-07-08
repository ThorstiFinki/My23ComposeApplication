package com.example.my23composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.my23composeapplication.chapters.FirstClick
import com.example.my23composeapplication.ui.theme.My23ComposeApplicationTheme

class MainActivity : ComponentActivity() {

    var firstClick = FirstClick()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            My23ComposeApplicationTheme {
                firstClick.MyFirstClickApp()
            }
        }
    }
}










