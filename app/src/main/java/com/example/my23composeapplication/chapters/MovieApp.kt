package com.example.my23composeapplication.chapters

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class MovieApp {


@Composable
fun MainContent() {
    Surface(color = MaterialTheme.colorScheme.background){

        Text(text = "Hello aus MovieApp")
    }
}}
