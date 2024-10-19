package com.example.learnhub.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learnhub.AuthViewModel
import com.example.learnhub.R


@Composable
fun SignUp(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Add padding to the screen
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.b),
            modifier = Modifier.size(200.dp),
            contentDescription = "Learn Hub Logo" // More descriptive
        )
        Text(
            text = "Welcome To Learn Hub",
            style = MaterialTheme.typography.headlineMedium, // Apply typography style
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp)) // Improved spacing
        Text(
            text = "SignUp to LearnHub ",
            style = MaterialTheme.typography.bodyLarge, // Apply typography style
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            modifier = Modifier.fillMaxWidth() // Make text field fill width
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth() // Make text field fill width
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {

        }) {
            Text("Signup")
        }
        Spacer(modifier = Modifier.height(32.dp))



        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = {
            navController.navigate("LoginPage")
        }) {
            Text("Already has a account")
        }
    }
}