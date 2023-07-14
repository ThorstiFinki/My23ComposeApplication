package com.example.my23composeapplication.chapters

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
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
import com.example.my23composeapplication.widgets.RoundIconButton

class TipApp {

    //@Preview
    @Composable
    fun MainContent() {
        BillForm() { billAmt ->
            Log.d("AMT", "MainContent: $billAmt")
        }

    }

    @Preview
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

        val sliderPositionState = remember {
            mutableStateOf(0f)
        }
        val tipPercentage = (sliderPositionState.value * 100).toInt()

        val splitByState = remember{
                mutableStateOf(1)}
        val range = IntRange(start =1, endInclusive = 100)
        val tipAmountState = remember{
            mutableStateOf(0.0)
        }
        val totalPerPersonState = remember {
            mutableStateOf(0.0)
        }


        Column(
            modifier = Modifier.padding(3.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            TopHeader(totalPerPerson = totalPerPersonState.value )

    Spacer(modifier = Modifier.height(1.dp))

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),

            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
            border = BorderStroke(width = 1.dp, color = Color.LightGray)
        )
        {

            Column(
                modifier = Modifier.padding(6.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                //TopHeader(123.00)

                InputField(
                    valueState = totalBillState,
                    labelId = "Enter Bill",
                    enabled = true,
                    isSingleLine = true,
                    onAction = KeyboardActions {
                        if (!validState) return@KeyboardActions
                        onValChange(totalBillState.value.trim())

                        keyboardController?.hide()
                    })
                  if (validState) {
                Row(
                    modifier = Modifier.padding(3.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Split", modifier = Modifier.align(
                            alignment = Alignment.CenterVertically,
                            )
                    )
                    Spacer(modifier = Modifier.width(120.dp))
                    Row(
                        modifier = Modifier.padding(horizontal = 3.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        RoundIconButton(
                            imageVector = Icons.Default.Remove,
                            onClick = {
                                splitByState.value =
                                    if (splitByState.value > 1) splitByState.value-1 else 1
                                totalPerPersonState.value =
                                    calculateTotalPerPerson(totalBill = totalBillState.value.toDouble(),
                                        splitBy =splitByState.value, tipPercentage = tipPercentage)
                            })
                        Text(
                            text = "${splitByState.value}",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 9.dp, end = 9.dp)
                        )

                        RoundIconButton(
                            imageVector = Icons.Default.Add,
                            onClick = {
                               if (splitByState.value < range.last){
                                    splitByState.value=splitByState.value+1
                                }
                                totalPerPersonState.value =
                                    calculateTotalPerPerson(totalBill = totalBillState.value.toDouble(),
                                        splitBy =splitByState.value, tipPercentage = tipPercentage)}
                            )
                    }
                }
                Row(
                    modifier = Modifier.padding(horizontal = 3.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Tip",
                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(200.dp))

                    Text(
                        text = "€ ${tipAmountState.value}",
                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text ="$tipPercentage %" )
                    Spacer(modifier = Modifier.height(14.dp))
                    Slider(
                        value = sliderPositionState.value,
                        onValueChange = { newVal ->
                            sliderPositionState.value = newVal
                            tipAmountState.value =
                                calculateTotalTip(totalBill = totalBillState.value.toDouble(), tipPercentage =tipPercentage)
                           totalPerPersonState.value =
                               calculateTotalPerPerson(totalBill = totalBillState.value.toDouble(),
                            splitBy =splitByState.value, tipPercentage = tipPercentage)
                        }, modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp
                        ),
                        steps = 5
                    )
                }
                 } else {
Box(modifier = Modifier)
                  }


            }
        }
        }}

    private fun calculateTotalTip(
        totalBill: Double,
        tipPercentage: Int
    ): Double {
        return if (totalBill > 1 && totalBill.toString().isNotEmpty()) {
            (totalBill * tipPercentage) / 100
        } else 0.0

    }

    fun calculateTotalPerPerson(
        totalBill: Double,
        splitBy: Int,
        tipPercentage: Int
    ): Double {
        val bill = calculateTotalTip(
            totalBill = totalBill,
            tipPercentage = tipPercentage
        ) + totalBill
return (bill/splitBy)
    }


    //    @Preview
   @Composable
    fun TopHeader(totalPerPerson: Double = 123.00) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
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
                    text = "€$total",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 50.sp
                )

            }

        }
    }
}