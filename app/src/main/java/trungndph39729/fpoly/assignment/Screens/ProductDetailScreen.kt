package trungndph39729.fpoly.assignment.Screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.project1762.Helper.ManagmentCart
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import trungndph39729.fpoly.assignment.Component.ImageSlider
import trungndph39729.fpoly.assignment.Component.MyButton
import trungndph39729.fpoly.assignment.R
import trungndph39729.fpoly.assignment.home_screen.HomeViewModel


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductDetailScreen(navController: NavController, id: String, homeViewModel: HomeViewModel) {
    val systemUiController = rememberSystemUiController()

    val productState = homeViewModel.products.observeAsState(initial = emptyList())
    val product = productState.value.find { it.id == id }

    val context = LocalContext.current
    val triggerRecomposition = remember { mutableStateOf(false) }

    val managerCart = remember {
        ManagmentCart(context) {
            triggerRecomposition.value = !triggerRecomposition.value

        }
    }


    if (product == null) {
        return
    }
    SideEffect {
        // Hide the status bar and navigation bar
        systemUiController.setSystemBarsColor(
            color = Color.Transparent, darkIcons = true
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 25.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    // Image Slider
                    product.picUrl?.let { ImageSlider(images = it, navController = navController) }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Product Info
                    Column(
                        modifier = Modifier.padding(end = 25.dp)
                    ) {

                        // Product Name
                        Text(
                            text = product.title,
                            fontSize = 24.sp,
                            color = Color(0xff303030),
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.W500
                        )

                        // Price and increment decrement
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "$ ${product.price}",
                                fontSize = 30.sp,
                                color = Color(0xff303030),
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.W700
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween

                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .background(
                                            Color(0xffe8e8e8),
                                            shape = RoundedCornerShape(6.dp)
                                        )
                                        .clickable { }, contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.plus),
                                        contentDescription = null,
                                        modifier = Modifier.padding(8.dp)

                                    )
                                }

                                Text(
                                    text = "01",
                                    fontSize = 18.sp,
                                    color = Color(0xff242424),
                                    fontWeight = FontWeight.W600,
                                    modifier = Modifier.padding(horizontal = 15.dp)
                                )
                                Box(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .background(
                                            Color(0xffe8e8e8),
                                            shape = RoundedCornerShape(6.dp)
                                        )
                                        .clickable { }, contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.minus),
                                        contentDescription = null,
                                        modifier = Modifier.padding(8.dp)


                                    )
                                }
                            }

                        }

                        // Review
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,

                            ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.star),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    text = product.rating.toString(),
                                    fontSize = 18.sp,
                                    color = Color(0xff303030),
                                    fontWeight = FontWeight.W700,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                            Spacer(modifier = Modifier.padding(horizontal = 20.dp))
                            Text(
                                text = "(50 reviews)",
                                fontSize = 14.sp,
                                color = Color(0xff808080),
                                fontWeight = FontWeight.W600,
                            )
                        }

                        // Description

                        product.description?.let {
                            Text(
                                text = it,
                                fontSize = 14.sp,
                                color = Color(0xff606060),
                                fontWeight = FontWeight.W300,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Justify,
                                lineHeight = 20.sp,
                                modifier = Modifier
                                    .padding(top = 15.dp)
                                    .wrapContentHeight()
                            )
                        }

                    }
                }


                // Add to cart
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,


                    ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(Color(0xffe8e8e8), shape = RoundedCornerShape(8.dp))
                            .clickable { }, contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.marker_1_1),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                                Color(0xff303030)
                            )

                        )
                    }
                    Spacer(modifier = Modifier.width(15.dp))

                    MyButton(
                        text = "Add to cart",
                        onTap = {
                            managerCart.insertItem(product)

                        },
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth()

                    )


                }
            }
        }
        Card(
            modifier = Modifier
                .systemBarsPadding()
                .size(40.dp)
                .clickable {
                    navController.popBackStack()
                },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),

            ) {
            Image(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),


                )
        }
    }
}


@Composable
fun IndicatorDot(
    modifier: Modifier = Modifier, width: Dp, color: Color
) {

    Box(
        modifier = modifier
            .width(width)
            .height(5.dp)
            .clip(RoundedCornerShape(50))
            .background(color)
            .animateContentSize(),
    )
}

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = Color(0xFF303030),
    unSelectedColor: Color = Color.White,
    selectedWidth: Dp = 30.dp,
    unSelectedWidth: Dp = 15.dp,
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unSelectedColor,
                width = if (index == selectedIndex) selectedWidth else unSelectedWidth,
            )

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    autoSlideDuration: Long = 3000L,
    pagerState: PagerState = remember { PagerState() },
    itemsCount: Int,
    itemContent: @Composable (index: Int) -> Unit,
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    LaunchedEffect(pagerState.currentPage) {
        delay(autoSlideDuration)
        pagerState.animateScrollToPage((pagerState.currentPage + 1) % itemsCount)
    }

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        HorizontalPager(count = itemsCount, state = pagerState) { page ->
            itemContent(page)
        }

        Surface(
            modifier = Modifier
                .padding(bottom = 30.dp, end = 40.dp)
                .align(Alignment.BottomEnd),
            color = Color.Transparent
        ) {
            DotsIndicator(
                totalDots = itemsCount,
                selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage,
            )
        }
    }
}

