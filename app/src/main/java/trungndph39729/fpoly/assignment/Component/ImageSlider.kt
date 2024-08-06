package trungndph39729.fpoly.assignment.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import trungndph39729.fpoly.assignment.R
import trungndph39729.fpoly.assignment.Screens.AutoSlidingCarousel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSlider(images: List<String>, navController: NavController) {
        Card(
            modifier = Modifier.padding(start = 20.dp),
            shape = RoundedCornerShape(bottomStart = 52.dp),
        ) {
            AutoSlidingCarousel(
                itemsCount = images.size,
                autoSlideDuration = 3000L,
                pagerState = remember { PagerState() },
                itemContent = { index ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(images[index])
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.height(430.dp)
                    )
                }
            )
    }
}

