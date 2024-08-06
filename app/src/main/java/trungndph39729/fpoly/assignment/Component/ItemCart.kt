package trungndph39729.fpoly.assignment.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.project1762.Helper.ManagmentCart
import trungndph39729.fpoly.assignment.Models.Product
import trungndph39729.fpoly.assignment.R
import trungndph39729.fpoly.assignment.helper.ChangeNumberItemsListener

@Composable
fun ItemCart(
    product: Product,
    managerCart: ManagmentCart,
    cartItems: SnapshotStateList<Product>


) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .height(100.dp),
    ) {
        AsyncImage(
            model = product.picUrl?.first(),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )
        Column(
            Modifier
                .fillMaxHeight()
                .padding(start = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text = product.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W600,
                    color = Color(0xff999999)
                )
                Image(
                    painterResource(id = R.drawable.close_btn),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            managerCart.removeItem(
                                product, object : ChangeNumberItemsListener {
                                    override fun changed() {
                                        cartItems.remove(product)

                                    }
                                })


                        }
                )

            }
            Text(
                text = "$ " + product.price.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
                color = Color(0xff242424)
            )
//            Quantity
            Row(

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(Color(0xffe8e8e8), shape = RoundedCornerShape(6.dp))
                        .clickable { }, contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                managerCart.plusItem(
                                    cartItems,
                                    cartItems.indexOf(product)
                                )
                            }

                    )
                }

                Text(
                    text = product.numberInCart.toString(),
                    fontSize = 18.sp,
                    color = Color(0xff242424),
                    fontWeight = FontWeight.W600,
                    modifier = Modifier.padding(horizontal = 15.dp)
                )
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(Color(0xffe8e8e8), shape = RoundedCornerShape(6.dp))
                        .clickable { }, contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.minus),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                managerCart.minusItem(
                                    cartItems,
                                    cartItems.indexOf(product)
                                )

                            }
                    )

                }
            }
        }

    }
}