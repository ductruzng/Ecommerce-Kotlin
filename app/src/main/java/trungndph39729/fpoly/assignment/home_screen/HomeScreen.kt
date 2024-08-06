package trungndph39729.fpoly.assignment.home_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import trungndph39729.fpoly.assignment.Component.CategoryItem
import trungndph39729.fpoly.assignment.Component.FixedGrid
import trungndph39729.fpoly.assignment.Models.CategoryModel
import trungndph39729.fpoly.assignment.R
import trungndph39729.fpoly.assignment.navigation.Screens

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel ) {

    val categoryState = homeViewModel.categories.observeAsState(initial = emptyList())
    val categories = categoryState.value

    val productState = homeViewModel.products.observeAsState(initial = emptyList())
    val product = productState.value

    var selectedIndex by remember { mutableStateOf(0) }

    if (categories.isEmpty()) {
      return
    }
    val filteredProducts = product.filter { it.idCategory == categories[selectedIndex]._id }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xfffdfdfd))
            .padding(start = 20.dp, end = 20.dp, top = 10.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(id = R.drawable.ri_search),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { navController.navigate(Screens.SearchProductScreen.route) }
                )

                Column(
                    modifier = Modifier
                        .width(120.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Make home",
                        fontSize = 16.sp,
                        color = Color(0xff909090),
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.W400
                    )
                    Text(
                        text = "BEAUTIFUL",
                        fontSize = 18.sp,
                        color = Color(0xff303030),
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.W700
                    )
                }
                Image(
                    painterResource(id = R.drawable.bi_cart2),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { navController.navigate(Screens.CartScreen.route) }
                )

            }
            LazyColumn {
                item {

                    Spacer(modifier = Modifier.height(10.dp))

                    LazyRow() {
                        itemsIndexed(categories) { index, item ->

                            val isSelected = index == selectedIndex
                            val textColor = if (isSelected) Color(0xff303030) else Color(0xFF909090)
                            val fontWeight = if (isSelected) FontWeight.W600 else FontWeight.W400
                            val imageColor = if (isSelected) Color.White else Color(0xFF999999)
                            val backgroundColor =
                                if (isSelected) Color(0xff303030) else Color(0xffF5F5F5)
                            CategoryItem(
                                backgroundColor = backgroundColor,
                                imageColor = imageColor,
                                textColor = textColor,
                                fontWeight = fontWeight,
                                category = item,
                                onTap = {
                                    selectedIndex = index
                                    Log.d("SelectedIndex", selectedIndex.toString())
                                }
                            )


                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    FixedGrid(
                        products = if (selectedIndex == 0) product else filteredProducts,
                        rows = product.size,
                        columns = 2,
                        navController = navController,
                    )
                }


            }
        }
    }
}

