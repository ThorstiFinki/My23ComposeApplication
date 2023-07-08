package com.example.my23composeapplication.chapters

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class FirstClick {


    @Composable
    fun MyFirstClickApp() {

        val moneyCounter = remember {
            mutableStateOf(0)
        }
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight(),
            color = Color(0xFF546E7A) ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Aktueller Wert: ${moneyCounter.value}", style = TextStyle(
                    color = Color.White,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                )
                Spacer(modifier = Modifier.height(30.dp))
                CreateCircle(moneyCounter = moneyCounter.value) {newValue ->
                    moneyCounter.value = newValue
                }

                if (moneyCounter.value >25) {
                    Text(text = "Lots of clicks!")
                }

            }
        }
    }

    @Composable
    fun CreateCircle(moneyCounter : Int = 0, updateMoneyCounter: (Int) -> Unit) {
        Card(
            modifier = Modifier
                .padding(3.dp)
                .size(105.dp)
                .clickable {
                    updateMoneyCounter(moneyCounter + 1)
                    Log.d("Tap", "CreateCircle: $moneyCounter")
                }, shape = CircleShape
        ) {
            Box(contentAlignment = Alignment.Center) {
                Spacer(modifier = Modifier.height(100.dp))
                Text(
                    text = "Click me!", modifier = Modifier,
                    style = TextStyle(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
        }
    }

}