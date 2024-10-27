package com.example.learnhub.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LearnHubBadge() {
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFF1E88E5), Color(0xFF42A5F5)) // Blue gradient
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
            .padding(bottom = 0.dp)
            .background(brush = gradient, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "LearnHub",
            color = Color.White,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun OS(){

    Column {
        LearnHubBadge()

        Text(text = "OS")


    }

}