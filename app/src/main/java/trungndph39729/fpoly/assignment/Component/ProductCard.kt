package trungndph39729.fpoly.assignment.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.project1762.Helper.ManagmentCart
import trungndph39729.fpoly.assignment.Models.Product
import trungndph39729.fpoly.assignment.R


@Composable
fun ProductCard(
    product: Product, onClick: () -> Unit
) {

    val context = LocalContext.current
    val managerCart: ManagmentCart = remember { ManagmentCart(context = context){} }

    Box(
        modifier = Modifier
            .width(170.dp)
            .wrapContentHeight()
            .padding(bottom = 15.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }

    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                AsyncImage(
                    model = product.picUrl?.get(0),
                    contentDescription = product.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Image(
                    painterResource(id = R.drawable.add_to_cart),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                        .size(30.dp)
                        .clickable {
                            managerCart.insertItem(product)

                        },

                    )
            }
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = product.title, fontSize = 14.sp,
                color = Color(0xff606060),
                fontWeight = FontWeight.W400,
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "$ ${product.price}",
                fontSize = 14.sp,
                color = Color(0xff303030),
                fontWeight = FontWeight.W700,
                modifier = Modifier.padding(bottom = 5.dp)
            )

        }

    }
}
