package trungndph39729.fpoly.assignment.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import trungndph39729.fpoly.assignment.Models.Order
import trungndph39729.fpoly.assignment.Models.OrderRequest
import trungndph39729.fpoly.assignment.R
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderCard(
   order : Order,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 25.dp, top = 10.dp),
        onClick = onClick

    ) {
        Column {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 15.dp, bottom = 15.dp, end = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text = "Order No ${Random( 100).nextInt()}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    color = Color(0xff242424)
                )
                order.createdAt?.let {
                    Text(
                        text = it.slice(0..9),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = Color(0xff808080)
                    )
                }

            }
            Divider(
                modifier = Modifier
                    .height(2.dp)
                    .background(color = Color(0xfff0f0f0))
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 15.dp, bottom = 15.dp, end = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Quantity: " ,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = Color(0xff808080)
                    )
                    Text(
                        text = "${order.products.size}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        color = Color(0xff242424)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Text(
                        text = "Total Amount: ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = Color(0xff808080)
                    )
                    Text(
                        text = "$ "+order.totalPrice.toString(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        color = Color(0xff242424)
                    )
                }
            }
            Row(
                Modifier
                    .padding(bottom = 20.dp, top = 30.dp, end = 15.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick =
                    { },
                    modifier = Modifier,

                    shape = RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp),

                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xff242424))
                ) {
                    Text(
                        "Detail", color = Color.White, fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                    )
                }
                Text(
                   order.status, color = Color(0xFFFF5722), fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                )
            }

        }

    }
}

