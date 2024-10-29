import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.learnhub.R

import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun LearnHubBadge() {
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFF1E88E5), Color(0xFF42A5F5)) // Blue gradient
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
            .padding(bottom = 0.dp)
            .background(brush = gradient, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "LearnHub",
            color = Color.White,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Home(modifier: Modifier = Modifier, navController: NavHostController) {
    Column(
        modifier = modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LearnHubBadge()
        Spacer(modifier = Modifier.height(16.dp))

        // List of items with their corresponding navigation routes and PDF URLs
        val itemsList: List<Triple<String, Int, String>> = listOf(
            Triple("Coding", R.drawable.coding, "https://firebasestorage.googleapis.com/v0/b/learnhub-611b0.appspot.com/o/Andrew%20S.%20Tanenbaum%20-%20Computer%20Networks.pdf?alt=media&token=1de6a8e1-2fa7-4cc2-ac52-c2b81ed64de0"),
            Triple("OS", R.drawable.os, "https://firebasestorage.googleapis.com/v0/b/learnhub-611b0.appspot.com/o/Andrew%20S.%20Tanenbaum%20-%20Computer%20Networks.pdf?alt=media&token=1de6a8e1-2fa7-4cc2-ac52-c2b81ed64de0"),
            Triple("DBMS", R.drawable.dbms, "https://firebasestorage.googleapis.com/v0/b/learnhub-611b0.appspot.com/o/Andrew%20S.%20Tanenbaum%20-%20Computer%20Networks.pdf?alt=media&token=1de6a8e1-2fa7-4cc2-ac52-c2b81ed64de0"),
            Triple("React", R.drawable.react, "https://firebasestorage.googleapis.com/v0/b/learnhub-611b0.appspot.com/o/Andrew%20S.%20Tanenbaum%20-%20Computer%20Networks.pdf?alt=media&token=1de6a8e1-2fa7-4cc2-ac52-c2b81ed64de0"),
            Triple("Android", R.drawable.android, "https://firebasestorage.googleapis.com/v0/b/learnhub-611b0.appspot.com/o/Andrew%20S.%20Tanenbaum%20-%20Computer%20Networks.pdf?alt=media&token=1de6a8e1-2fa7-4cc2-ac52-c2b81ed64de0"),
            Triple("TOC", R.drawable.toc, "https://firebasestorage.googleapis.com/v0/b/learnhub-611b0.appspot.com/o/Andrew%20S.%20Tanenbaum%20-%20Computer%20Networks.pdf?alt=media&token=1de6a8e1-2fa7-4cc2-ac52-c2b81ed64de0"),
            Triple("ML", R.drawable.ml, "https://firebasestorage.googleapis.com/v0/b/learnhub-611b0.appspot.com/o/Andrew%20S.%20Tanenbaum%20-%20Computer%20Networks.pdf?alt=media&token=1de6a8e1-2fa7-4cc2-ac52-c2b81ed64de0"),
            Triple("Computer Networks", R.drawable.todo, "https://firebasestorage.googleapis.com/v0/b/learnhub-611b0.appspot.com/o/Andrew%20S.%20Tanenbaum%20-%20Computer%20Networks.pdf?alt=media&token=1de6a8e1-2fa7-4cc2-ac52-c2b81ed64de0")
        )


        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(itemsList) { (label, imageResId, pdfUrl) ->
                GridItemWithImageAndText(label = label, imageResId = imageResId) {
                    // Encode URL before navigating
                    val encodedUrl = Uri.encode(pdfUrl)
                    navController.navigate("pdf_viewer/$encodedUrl")
                }
            }
        }
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

        // Label under each image
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
@Composable
fun FirebasePdfWebView(pdfUrl: String) {
    val pdfDriveViewerUrl = "https://drive.google.com/viewerng/viewer?embedded=true&url=https://firebasestorage.googleapis.com/v0/b/learnhub-611b0.appspot.com/o/dbms%20notes.pdf?alt=media&token=d0469817-97b8-4407-a1b5-f0d700bc35db"

    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(pdfDriveViewerUrl) // Load the Firebase PDF URL through Google Drive PDF Viewer
        }
    }, modifier = Modifier.fillMaxSize())
}

@Composable
fun PDF() {

    Column(modifier = Modifier.fillMaxSize().padding(top = 40.dp)) {


        // Call the PDF viewer with the Firebase URL
        FirebasePdfWebView(
            pdfUrl = "https://firebasestorage.googleapis.com/v0/b/learnhub-611b0.appspot.com/o/dbms%20notes.pdf?alt=media&token=d0469817-97b8-4407-a1b5-f0d700bc35db"
        )
    }
}
