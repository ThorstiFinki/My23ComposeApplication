package com.example.my23composeapplication.chapters

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.my23composeapplication.components.InputField

class TipApp {
    // @Preview
    @Composable
    fun MyTipApp(
    ) {

    }

    //  @Preview
    @Composable
    fun TopHeader(totalPerPerson: Double = 123.00) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(shape = CircleShape.copy(all = CornerSize(12.dp))),
            //.clip(shape = RoundedCornerShape(corner= CornerSize(12.dp))))
            color = Color(0xFFB980E6)
        )
        {
            Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val total = "%.2f".format(totalPerPerson)
                Text(
                    text = "Total Per Person",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 40.sp
                )
                Text(
                    text = "$$total",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 50.sp
                )

            }

        }
    }


    @Preview
    @Composable
    fun MainContent() {
        BillForm() { billAmt ->
            Log.d("AMT", "MainContent: $billAmt")
        }


    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun BillForm(
        modifier: Modifier = Modifier,
        onValChange: (String) -> Unit = {}
    ) {
        val totalBillState = remember {
            mutableStateOf("")
        }
        val validState = remember(totalBillState.value) {
            totalBillState.value.trim().isNotEmpty()
        }
        val keyboardController = LocalSoftwareKeyboardController.current
        Surface(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
            border = BorderStroke(width = 1.dp, color = Color.LightGray)
        )
        {
            Column() {

                InputField(valueState = totalBillState,
                    labelId = "Enter Bill",
                    enabled = true,
                    isSingleLine = true,
                    onAction = KeyboardActions {
                        if (!validState) return@KeyboardActions
                        onValChange(totalBillState.value.trim())

                        keyboardController?.hide()
                    }
                )
            }


        }
    }
}
