package com.example.learnhub.pages

import Home
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.learnhub.AuthViewModel
import com.example.learnhub.ui.theme.navItem

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavHostController, // Use NavHostController
    authViewModel: AuthViewModel
) {
    val navItemsList = listOf(
        navItem("Home", Icons.Default.Home),
        navItem("Notification", Icons.Default.Notifications),
        navItem("TO-DO", Icons.Default.List)
    )

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemsList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        label = { Text(text = navItem.label) },
                        icon = { Icon(imageVector = navItem.icon, contentDescription = "Icon") }
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(
            modifier = Modifier.padding(innerPadding),
            selectedIndex,
            navController // Pass navController to ContentScreen
        )
    }

    val authState by authViewModel.authState.observeAsState()
    LaunchedEffect(authState) {
        if (authState is AuthViewModel.AuthState.Unauthenticated) { // Check for Unauthenticated state
            navController.navigate("LoginPage")
        }
    }
}

@Composable
fun ContentScreen(
    modifier: Modifier,
    selectedIndex: Int,
    navController: NavHostController // Use NavHostController
) {
    when (selectedIndex) {
        0 -> Home(modifier = modifier, navController = navController)
        1 -> Notification(modifier = modifier)
        2 -> ComputerNetworks(modifier=modifier)
    }
}