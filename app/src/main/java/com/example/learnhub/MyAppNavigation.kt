package com.example.learnhub

import Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learnhub.pages.Android
import com.example.learnhub.pages.Coding
import com.example.learnhub.pages.HomePage
import com.example.learnhub.pages.LoginPage
import com.example.learnhub.pages.Coding
import com.example.learnhub.pages.ComputerNetworks
import com.example.learnhub.pages.DBMS
import com.example.learnhub.pages.ML
import com.example.learnhub.pages.OS
import com.example.learnhub.pages.React
import com.example.learnhub.pages.SignUp
import com.example.learnhub.pages.TOC


@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "LoginPage") {
        composable("LoginPage") { LoginPage(modifier, navController, authViewModel) }
        composable("SignUp") { SignUp(modifier, navController, authViewModel) }
        composable("HomePage") { HomePage(modifier, navController, authViewModel) }
        composable("Home") { Home(navController = navController) }
        composable("Coding") { Coding() }
        composable("OS") { OS()}
        composable("DBMS") { DBMS()}
        composable("React") { React() }
        composable("Android") { Android()}
        composable("TOC") { TOC() }
        composable("ML") { ML() }
        composable("Computer Networks") { ComputerNetworks() }
    }
}