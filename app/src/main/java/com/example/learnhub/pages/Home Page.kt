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

@Composable
fun LearnHubBadge() {
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFF1E88E5), Color(0xFF42A5F5)) // Blue gradient
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
            .padding(bottom = 0.dp) // Increased bottom padding for better spacing
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
        modifier = modifier.fillMaxSize().padding(8.dp), // Fill the max size and apply padding
        horizontalAlignment = Alignment.CenterHorizontally // Center align items horizontally
    ) {
        LearnHubBadge()

        // Spacer for better vertical alignment
        Spacer(modifier = Modifier.height(16.dp))

        val itemsList: List<Pair<String, Int>> = listOf(
            "Coding" to R.drawable.coding,
            "OS" to R.drawable.os,
            "DBMS" to R.drawable.dbms,
            "React" to R.drawable.react,
            "Android" to R.drawable.android,
            "TOC" to R.drawable.toc,
            "ML" to R.drawable.ml,
            "To-Do" to R.drawable.todo
        )

        // Display the grid of clickable image buttons
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(itemsList) { (label, imageResId) ->
                GridItemWithImageAndText(label = label, imageResId = imageResId) {
                    navController.navigate("SecondActivity")
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
            .fillMaxWidth() // Ensure each grid item takes the available width
    ) {
        Image(
            painter = painterResource(imageResId),
            contentDescription = label,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp) // Fixed size for images
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
