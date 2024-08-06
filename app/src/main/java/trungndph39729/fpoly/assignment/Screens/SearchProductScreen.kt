package trungndph39729.fpoly.assignment.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import trungndph39729.fpoly.assignment.Component.ProductCard
import trungndph39729.fpoly.assignment.R
import trungndph39729.fpoly.assignment.home_screen.HomeViewModel
import trungndph39729.fpoly.assignment.navigation.Screens

@Composable
fun SearchProductScreen(navController: NavController, homeViewModel: HomeViewModel) {

    var searchValue by rememberSaveable {
        mutableStateOf("")
    }

    val productState = homeViewModel.products.observeAsState(initial = emptyList())
    val products = productState.value


    var filteredProducts by remember {
        mutableStateOf(products)
    }
    Column(
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
//        TopAppBar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp)

                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = R.drawable.back_arrow),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.width(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xff242424), RoundedCornerShape(10.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    TextField(
                        value = searchValue,
                        onValueChange = { searchValue = it },
                        placeholder = {
                            Text(
                                "Search anything",
                                fontWeight = FontWeight.W400,
                                fontSize = 16.sp,
                                color = Color(0xff999999)

                            )
                        },
                        modifier = Modifier
                            .weight(1f),
                        maxLines = 1,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Black,
                            cursorColor = Color(0xff303030),

                            ),

                        )

                    Box(
                        modifier = Modifier
                            .width(50.dp)
                            .fillMaxHeight()
                            .background(
                                Color(0xff303030),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clickable {

                                filteredProducts = products.filter { product ->
                                    product.title.contains(searchValue, ignoreCase = true)
                                }
                            },
                        contentAlignment = Alignment.Center,

                        ) {
                        Image(
                            painter = painterResource(id = R.drawable.ri_search),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            contentScale = ContentScale.Crop,
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                }
            }


        }

//        List
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp),
        ) {
            Text(
                text = "Recent Search",
                fontSize = 18.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W600,
                color = Color(0xff242424),
                modifier = Modifier.padding(bottom = 10.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                items(filteredProducts) {
                    ProductCard(product = it) {
                        navController.navigate("${Screens.DetailScreen.route}/${it.id}")
                    }
                }
            }
        }

    }
}

