package com.example.learnhub.pages

import AuthViewModel
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.learnhub.R



@Composable
fun  LoginPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current
    LaunchedEffect  (authState.value is AuthViewModel.AuthState.Authenticated) {
        when (authState.value) {
            is AuthViewModel.AuthState.Authenticated -> {
                navController.navigate("HomePage")
            }

            is AuthViewModel.AuthState.Error -> {
                val errorMessage = (authState.value as AuthViewModel.AuthState.Error).message
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            }

            else -> Unit
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.a),
            modifier = Modifier.size(200.dp),
            contentDescription = "Learn Hub Logo"
        )
        Text(
            text = "Welcome To Learn Hub",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Login to your account",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true // Ensures the field is single-line
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true // Ensures the field is single-line
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            authViewModel.login(email, password)
        },
        enabled = authState.value != AuthViewModel.AuthState.Loading)
        {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = {
           navController.navigate("SignUp")
        }) {
            Text("Create an account")
        }
    }
}
