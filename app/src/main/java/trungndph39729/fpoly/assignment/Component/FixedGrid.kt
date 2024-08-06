package trungndph39729.fpoly.assignment.Component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import trungndph39729.fpoly.assignment.Models.Product
import trungndph39729.fpoly.assignment.navigation.Screens

@Composable
fun FixedGrid(
    products: List<Product>,
    rows: Int,
    columns: Int,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Column(modifier = modifier) {
        for (row in 0 until rows) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (col in 0 until columns) {
                    val index = row * columns + col
                    if (index < products.size) {
                        ProductCard(product = products[index]) {
                            navController.navigate("${Screens.DetailScreen.route}/${products[index].id}")
                        }


                    }
                }
            }
        }
    }
}