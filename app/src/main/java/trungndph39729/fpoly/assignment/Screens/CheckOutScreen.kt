package trungndph39729.fpoly.assignment.Screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.project1762.Helper.ManagmentCart
import trungndph39729.fpoly.assignment.Component.MyButton
import trungndph39729.fpoly.assignment.Models.OrderRequest
import trungndph39729.fpoly.assignment.R
import trungndph39729.fpoly.assignment.login_screen.SignInViewModel
import trungndph39729.fpoly.assignment.navigation.Screens
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckOutScreen(navController: NavController, signInViewModel: SignInViewModel) {
    val triggerRecomposition = remember { mutableStateOf(false) }
    val context = LocalContext.current

    val managerCart: ManagmentCart = remember {
        ManagmentCart(context = context) {
            triggerRecomposition.value = !triggerRecomposition.value
        }
    }
    val total = managerCart.getTotalFee() + 10

    val user by signInViewModel.user.observeAsState()

    if (user?.addresses!!.isEmpty()) {


        return Toast.makeText(context, "Please add your address", Toast.LENGTH_SHORT).show().also {
            navController.navigate(Screens.ShippingAddressScreen.route)
        }

    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    "Check out",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.fillMaxWidth()
                )
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xffffffff)
            ), navigationIcon = {
                Image(
                    painterResource(id = R.drawable.back_arrow),
                    contentDescription = null,
                    Modifier
                        .size(20.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
            }, modifier = Modifier.padding(horizontal = 20.dp), actions = {}

            )
        },
        containerColor = Color(0xffffffff),
    ) {
        it
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            // Delivery address
            item {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Shipping Address",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W600,
                            color = Color(0xff909090),

                            )
                        Image(
                            painterResource(id = R.drawable.edit_btn),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth()
                    ) {
                        Column {
                            user?.addresses?.get(0)?.let { it1 ->
                                Text(
                                    text = it1.name,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.W700,
                                    color = Color(0xff303030),
                                    modifier = Modifier.padding(15.dp)
                                )

                                Divider(
                                    color = Color(0xfff0f0f0), // Màu của dòng kẻ
                                    thickness = 2.dp // Chiều cao của dòng kẻ
                                )

                                Text(
                                    text = "${it1.address}, ${it1.city}",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W400,
                                    color = Color(0xff808080),
                                    modifier = Modifier.padding(15.dp)
                                )
                            }

                        }
                    }
                }
            }

            // Payment method
            item {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Payment",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W600,
                            color = Color(0xff909090),

                            )
                        Image(
                            painterResource(id = R.drawable.edit_btn),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            Modifier.padding(horizontal = 20.dp, vertical = 15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Image(
                                painterResource(id = R.drawable.card),
                                contentDescription = null,
                                modifier = Modifier.size(70.dp, 45.dp),
                                contentScale = ContentScale.None,


                                )
                            Spacer(modifier = Modifier.size(15.dp))
                            Column {
                                Text(
                                    text = "**** **** **** 1234",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W600,
                                    color = Color(0xff242424),

                                    )

                            }

                        }
                    }
                }
            }

            // Delivery method
            item {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Delivery method",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W600,
                            color = Color(0xff909090),

                            )
                        Image(
                            painterResource(id = R.drawable.edit_btn),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth()
                    ) {

                        Row(
                            Modifier.padding(horizontal = 20.dp, vertical = 15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Image(
                                painterResource(id = R.drawable.dhl),
                                contentDescription = null,
                                modifier = Modifier.size(90.dp, 20.dp),
                                contentScale = ContentScale.Crop,


                                )
                            Spacer(modifier = Modifier.size(15.dp))
                            Column {
                                Text(
                                    text = "Fast (2-3days)",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W700,
                                    color = Color(0xff303030),

                                    )

                            }

                        }
                    }
                }
            }

            // Order summary
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 25.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        Modifier
                            .padding(horizontal = 20.dp, vertical = 15.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Order: ",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W400,
                                color = Color(0xff808080),

                                )
                            Text(
                                text = "$ ${managerCart.getTotalFee()}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W400,
                                color = Color(0xff242424),

                                )
                        }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Delivery: ",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W400,
                                color = Color(0xff808080),

                                )
                            Text(
                                text = "$ 10.00",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W400,
                                color = Color(0xff242424),

                                )
                        }
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Total: ",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W400,
                                color = Color(0xff808080),

                                )
                            Text(
                                text = "$ " + total,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W400,
                                color = Color(0xff242424),

                                )
                        }
                    }


                }
            }
            item {
                Box(modifier = Modifier.padding(20.dp)) {
                    MyButton(
                        text = "SUBMIT ORDER",
                        onTap = {
                            signInViewModel.addOrder(
                                OrderRequest(
                                    user = user!!,
                                    products = managerCart.getListCart(),
                                    totalPrice = total

                                )
                            )

                            navController.popBackStack()
                            managerCart.clearCart()
                            navController.navigate(Screens.SuccessScreen.route)
                        },
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth()


                    )
                }


            }


        }
    }
}

fun getCurrentDate(): Calendar {
    return Calendar.getInstance()
}

fun getToday(): String {
    val currentDate = getCurrentDate()
    val year = currentDate.get(Calendar.YEAR)
    val month = currentDate.get(Calendar.MONTH) + 1 // Tháng bắt đầu từ 0
    val dayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH)
    val today = "$year/$month/$dayOfMonth"
    return today
}

