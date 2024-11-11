import android.annotation.SuppressLint
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.learnhub.AuthViewModel
import com.example.learnhub.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val authState by authViewModel.authState.observeAsState()

    LaunchedEffect(authState) {
        if (authState is AuthViewModel.AuthState.Unauthenticated) {
            navController.navigate("LoginPage") {
                popUpTo("Home") { inclusive = true }
            }
        }
    }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = {
                scope.launch {
                    if (drawerState.isOpen) drawerState.close() else drawerState.open()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.Black

                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "      Learn Hub",
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 0.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                if (drawerState.isOpen) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(300.dp) // Increase drawer width
                            .background(Color.White)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Learn Hub",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Home",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    scope.launch { drawerState.close() }
                                }
                        )
                        Text(
                            text = "Logout",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    scope.launch {
                                        authViewModel.logout()
                                        drawerState.close()
                                    }
                                }
                        )
                    }
                }
            },
            content = {
                // List of items with their corresponding navigation routes and PDF URLs
                val itemsList = listOf(
                    Triple("Coding", R.drawable.coding, "https://firebasestorage.googleapis.com/..."),
                    Triple("OS", R.drawable.os, "https://firebasestorage.googleapis.com/..."),
                    Triple("DBMS", R.drawable.dbms, "https://firebasestorage.googleapis.com/..."),
                    Triple("COA", R.drawable.coa, "https://firebasestorage.googleapis.com/..."),
                    Triple("Android", R.drawable.android, "https://firebasestorage.googleapis.com/..."),
                    Triple("Python", R.drawable.python, "https://firebasestorage.googleapis.com/..."),
                    Triple("ML", R.drawable.ml, "https://firebasestorage.googleapis.com/..."),
                    Triple("Computer Networks", R.drawable.computernetworks, "https://firebasestorage.googleapis.com/...")
                )

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 128.dp),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    items(itemsList) { (label, imageResId, pdfUrl) ->
                        GridItemWithImageAndText(label = label, imageResId = imageResId) {
                            val encodedUrl = Uri.encode(pdfUrl)
                            navController.navigate("pdf_viewer/$encodedUrl")
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun GridItemWithImageAndText(label: String, imageResId: Int, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(imageResId),
            contentDescription = label,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
                .padding(8.dp)
        )

        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun PdfViewerScreen(pdfUrl: String) {
    FirebasePdfWebView(pdfUrl)
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun FirebasePdfWebView(pdfUrl: String) {
    val pdfDriveViewerUrl = "https://drive.google.com/viewerng/viewer?embedded=true&url=$pdfUrl"
    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(pdfDriveViewerUrl)
        }
    }, modifier = Modifier.fillMaxSize())
}

@Composable
fun PDF() {
    Column(modifier = Modifier.fillMaxSize().padding(top = 40.dp)) {
        FirebasePdfWebView(
            pdfUrl = "https://firebasestorage.googleapis.com/..."
        )
    }
}
