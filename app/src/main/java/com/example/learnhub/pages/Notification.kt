package com.example.learnhub.pages

import android.app.Notification
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun Notification(modifier: Modifier = Modifier.fillMaxSize())
{
    Text(text = "Notification Page", fontSize = 24.sp)
}