package com.example.quickcalc

import android.content.Context
import android.graphics.drawable.Icon
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickcalc.ui.theme.PrimaryBackGroundColor
import com.example.quickcalc.ui.theme.PrimaryUiColor
import com.example.quickcalc.ui.theme.QuickCalcTheme
import com.example.quickcalc.ui.theme.SecondaryUiColor
import com.example.quickcalc.ui.theme.blue1
import com.example.quickcalc.ui.theme.blue2
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickCalcTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {

                    Box(Modifier.fillMaxSize()) {
                        Canvas(modifier = Modifier.fillMaxSize()){
                            val brush = Brush.linearGradient(
                                colors = listOf(blue1, blue2, SecondaryUiColor),
                                start = Offset(0f, 0f), // Start point of the gradient
                                end = Offset(size.width, size.height) // End point of the gradient
                            )

                            drawRect(
                                brush = brush,
                                size = size,
                            )
                        }
                        Calculate()
                        
                    }


                }
            }
        }
    }
}


fun evaluateMathExpression(expression: String): Double? {
    return try {
        val result = ExpressionBuilder(expression)
            .build()
            .evaluate()

        result
    } catch (e: Exception) {
        Log.d("App","Error")
        null
    }
}

fun removeLastCharacter(input: String): String {
    return input.dropLast(1)
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun Calculate(){


    var exp by remember { mutableStateOf("") }
    val fontsize = 25.sp

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Display(exp)

        Spacer(modifier = Modifier.height(100.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly) {

            Button(onClick = { exp += '1'  }, shape = RectangleShape
                , modifier = Modifier
                    .size(80.dp, 50.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    )
            ,colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)

            ) {
                Text(text ="1", fontSize = fontsize)
            }
            Button(onClick = {  exp += '2' }, shape = RectangleShape,
                modifier = Modifier
                    .size(80.dp, 50.dp)
                    .clip(RoundedCornerShape(15.dp))
                ,colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Text(text = "2", fontSize = fontsize)
            }
            Button(onClick = { exp += '3'}, shape = RectangleShape,
                modifier = Modifier
                    .size(80.dp, 50.dp)
                    .clip(RoundedCornerShape(15.dp))
                ,colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Text(text = "3", fontSize = fontsize)
            }
            Button(onClick = {  exp += '/' }, shape = RectangleShape,
                modifier = Modifier
                    .size(80.dp, 50.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    ),colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Text(text = "/", fontSize = fontsize)
            }

        }
        /////
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {  exp += '4' }, shape = RectangleShape,
                modifier = Modifier
                    .size(80.dp, 50.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    ),colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Text(text = "4", fontSize = fontsize)
            }
            Button(onClick = {  exp += '5' }, shape = RectangleShape,
                modifier = Modifier
                    .size(80.dp, 50.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    ),colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Text(text = "5", fontSize = fontsize)
            }
            Button(onClick = {  exp += '6' }, shape = RectangleShape,
                modifier = Modifier
                    .size(80.dp, 50.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    ),colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Text(text = "6", fontSize = fontsize)
            }
            Button(onClick = { exp += '*' }, shape = RectangleShape,
                modifier = Modifier
                    .size(80.dp, 50.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    ),colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Text(text = "*", fontSize = fontsize)
            }

        }

        ////////////////
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {  exp += '7' }, shape = RectangleShape,
                modifier = Modifier
                    .size(80.dp, 50.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    ),colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Text(text = "7", fontSize = fontsize)
            }
            Button(onClick = {  exp += '8' }, shape = RectangleShape,
                modifier = Modifier
                    .size(80.dp, 50.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    ),colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Text(text = "8", fontSize = fontsize)
            }
            Button(onClick = {  exp += '9' }, shape = RectangleShape,
                modifier = Modifier
                    .size(80.dp, 50.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    ),colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Text(text = "9", fontSize = fontsize)
            }
            Button(onClick = {  exp += '+' }, shape = RectangleShape,
                modifier = Modifier
                    .size(80.dp, 50.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    ),colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Text(text = "+", fontSize = fontsize)
            }

        }
        Row(Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly) {

            Button(onClick = {   exp += '0'}, shape = RectangleShape,
                modifier = Modifier
                    .size(80.dp, 50.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    ),colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Text(text = "0", fontSize = fontsize)
            }

            Button(onClick = {  exp += '.' }, shape = RectangleShape,
                modifier = Modifier
                    .size(80.dp, 50.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    ),colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Text(text = ".", fontSize = fontsize)
            }


               Icon(imageVector = Icons.Default.Backspace, contentDescription ="", modifier = Modifier
                   .combinedClickable(
                       onClick = { exp = removeLastCharacter(exp) },
                       onLongClick = { exp = "" }
                   )
                   .size(80.dp, 50.dp), tint = Color.White )

            Button(onClick = {  exp += '-' }, shape = RectangleShape,
                modifier = Modifier
                    .size(80.dp, 50.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    ),colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Text(text = "_", fontSize = 25.sp)
            }




        }

        Button(onClick = { val result = evaluateMathExpression(exp)
            if (result != null) {
                exp = result.toString()
            } else {
                exp =""
            }  }, shape = RectangleShape,
            modifier = Modifier
                .size(180.dp, 90.dp).padding(10.dp)
                .clip(
                    RoundedCornerShape(15.dp)
                ),colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Text(text = "=", fontSize = 50.sp)
        }

    }

}

@Composable
fun Display(value: String) {

    Card(
        Modifier
            .fillMaxWidth()
            .size(300.dp),
    ) {
            Box ( modifier = Modifier
                ){

                Canvas(modifier = Modifier.fillMaxSize()){

                val brush = Brush.linearGradient(
                    colors = listOf(PrimaryBackGroundColor, blue2),
                    start = Offset(0f, 0f), // Start point of the gradient
                    end = Offset(size.width, size.height) // End point of the gradient
                )

                drawRect(
                    brush = brush,
                    size = size,
                )

                 }

                Text(text = value, modifier = Modifier.padding(10.dp),
                    color = Color.Black, fontSize = 50.sp
                    ,lineHeight = 50.sp * 1.2f
                    , maxLines = 4
                    , overflow = TextOverflow.Visible
                )
            }




    }

}

