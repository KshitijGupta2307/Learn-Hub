package com.example.learnhub

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learnhub.pages.LoginPage
import com.example.learnhub.pages.SignUp
import com.example.learnhub.pages.HomePage

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "LoginPage", builder = {
        composable("LoginPage")
        {
            LoginPage(modifier, navController, authViewModel)
        }
        composable("SignUp")
        {
            SignUp(modifier, navController, authViewModel)
        }
        composable("HomePage")
        {
            HomePage(modifier, navController, authViewModel)
        }


    })
}
