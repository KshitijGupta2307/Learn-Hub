package com.example.learnhub.pages
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView


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
fun DBMS() {

    Column(modifier = Modifier.fillMaxSize().padding(top = 40.dp)) {

        LearnHubBadge()
        // Call the PDF viewer with the Firebase URL
        FirebasePdfWebView(
            pdfUrl = "https://firebasestorage.googleapis.com/v0/b/learnhub-611b0.appspot.com/o/dbms%20notes.pdf?alt=media&token=d0469817-97b8-4407-a1b5-f0d700bc35db"
        )
    }
}
