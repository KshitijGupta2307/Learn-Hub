package com.example.learnhub.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learnhub.AuthViewModel

@Composable
fun HomePage(modifier: Modifier= Modifier ,navController: NavController ,authViewModel: AuthViewModel) {
 val authState = authViewModel.authState.observeAsState()
    LaunchedEffect (authState.value){
        when (authState.value) {
            is AuthViewModel.AuthState.Unauthenticated ->
                navController.navigate("loginPage")
            else -> Unit
            }

        }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text = "Home Page",fontSize = 24.sp)
        TextButton(
            onClick = { authViewModel.logout() }
        ) {
            Text("signout")
        }
    }
    }
