import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.learnhub.pages.HomePage
import com.example.learnhub.pages.LoginPage
import com.example.learnhub.pages.SignUp


@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    val authState by authViewModel.authState.observeAsState(AuthViewModel.AuthState.Unauthenticated)

    // Choose the initial route based on authentication state
    val startDestination = if (authState == AuthViewModel.AuthState.Authenticated) "HomePage" else "LoginPage"

    NavHost(navController = navController, startDestination = startDestination) {
        composable("LoginPage") { LoginPage(modifier, navController, authViewModel) }
        composable("SignUp") { SignUp(modifier, navController, authViewModel) }

        composable("HomePage") {
            HomePage(
                modifier = modifier,
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable("pdf_viewer/{pdfUrl}") { backStackEntry ->
            val pdfUrl = Uri.decode(backStackEntry.arguments?.getString("pdfUrl") ?: "")
            PdfViewerScreen(pdfUrl = pdfUrl)
        }
    }
}