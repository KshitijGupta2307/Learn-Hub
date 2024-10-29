package com.example.learnhub

import Home
import PdfViewerScreen
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.learnhub.pages.HomePage
import com.example.learnhub.pages.LoginPage

import com.example.learnhub.pages.SignUp


@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "LoginPage") {
        composable("LoginPage") { LoginPage(modifier, navController, authViewModel) }
        composable("SignUp") { SignUp(modifier, navController, authViewModel) }
        composable("HomePage") { HomePage(modifier, navController, authViewModel) }
        composable("Home") { Home(navController = navController) }

        composable("pdf_viewer/{pdfUrl}") { backStackEntry ->
            val pdfUrl = Uri.decode(backStackEntry.arguments?.getString("pdfUrl") ?: "")
            PdfViewerScreen(pdfUrl = pdfUrl)
        }
    }
}
