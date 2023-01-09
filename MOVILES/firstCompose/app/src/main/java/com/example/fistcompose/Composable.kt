package com.example.fistcompose

import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hello $name!",
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.W600,
        style = MaterialTheme.typography.h4.copy(
            shadow = Shadow(blurRadius = 5f)
        ),
        textAlign = TextAlign.Center,
        modifier = modifier
            .background(Color.Gray)


    )
}