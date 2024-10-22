package com.example.learnhub.pages

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
import com.example.learnhub.AuthViewModel
import com.example.learnhub.ui.theme.navItem


@SuppressLint("SuspiciousIndentation")
@Composable
fun HomePage(modifier: Modifier= Modifier ,navController: NavController ,authViewModel: AuthViewModel) {
  val navItemsList = listOf(
      navItem("Home", Icons.Default.Home),
      navItem("Notification", Icons.Default.Notifications),
      navItem("TO-DO", Icons.Default.List)
      )

    var selectIndex by remember {
        mutableStateOf(0)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
           NavigationBar{
               navItemsList.forEachIndexed { index, navItem ->
                   NavigationBarItem(
        selected = selectIndex == index,
        onClick = { selectIndex = index },
        label = { Text(text = navItem.label)},
        icon = {
            Icon(imageVector = navItem.icon, contentDescription = "Icon")
        }
    )
}
               }
         }
    ) { innerpadding ->
        ContentScreen(modifier = Modifier.padding(innerpadding),selectIndex)
    }

    val authState = authViewModel.authState.observeAsState()
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthViewModel.AuthState.Unauthenticated ->
                navController.navigate("loginPage")

            else -> Unit
        }

    }
 }



@Composable
fun ContentScreen(modifier: Modifier , selectedIndex: Int) {
    when (selectedIndex) {
        0-> Home(modifier = modifier, authViewModel = AuthViewModel())
        1 -> Notification(modifier = modifier)
        2 -> ToDo(modifier)
    }
}

