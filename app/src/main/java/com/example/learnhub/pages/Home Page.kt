import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import com.example.learnhub.R

@Composable
fun LearnHubBadge() {
    // Gradient background for the badge
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFF1E88E5), Color(0xFF42A5F5)) // Blue gradient
    )

    Box(
        modifier = Modifier
            .fillMaxWidth() // Make the badge fill the entire width
            .padding(16.dp)
            .background(brush = gradient, shape = RoundedCornerShape(12.dp)) // Blue gradient with rounded corners
            .padding(horizontal = 16.dp, vertical = 8.dp), // Padding inside the badge
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "LearnHub",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Home(modifier: Modifier = Modifier) {
    val itemsList = listOf(
        "Coding C",
        "Operating System",
        "DBMS",
        "React JS",
        "Android",
        "TOC",
        "ML",
        "todo"
    )

    Column(
        modifier = modifier
            .fillMaxSize() // Fill available size
            .padding(8.dp)
    ) {
        // Add the LearnHub Badge at the top
        LearnHubBadge()

        // LazyVerticalGrid to display items in a grid
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp), // Adaptive grid cells
            modifier = Modifier
                .fillMaxSize(), // Fill available size
            contentPadding = PaddingValues(
                top = 8.dp,
                bottom = 64.dp // Bottom padding to accommodate bottom bar
            )
        ) {
            items(itemsList) { item ->
                GridItemWithImageAndText(item)
            }
        }
    }
}

@Composable
fun GridItemWithImageAndText(item: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        // Image with fixed size
        Image(
            painter = painterResource(id = R.drawable.b), // Replace with your image resource
            contentDescription = null,
            modifier = Modifier
                .size(120.dp) // Set a fixed size for the image
                .padding(bottom = 8.dp),
            contentScale = ContentScale.Crop
        )

        // Text below the image
        Text(
            text = item,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )
    }
}

