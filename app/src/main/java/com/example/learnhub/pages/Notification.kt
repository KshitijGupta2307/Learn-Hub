package com.example.learnhub.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Notification(modifier: Modifier = Modifier.fillMaxSize()) {
    var recommendedCourses by remember {
        mutableStateOf(
            listOf(
                Course("Kotlin for Beginners", "Learn Kotlin programming from scratch"),
                Course("Android Development", "Build your first Android app with Jetpack Compose"),
                Course("Data Structures and Algorithms", "Master the art of problem-solving"),
                Course("Machine Learning", "Dive into the world of ML and AI concepts")
            )
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Latest Course Recommendations",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(recommendedCourses) { course ->
                CourseCard(course)
            }
        }
    }
}

@Composable
fun CourseCard(course: Course) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = course.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = course.description,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}

data class Course(val name: String, val description: String)
