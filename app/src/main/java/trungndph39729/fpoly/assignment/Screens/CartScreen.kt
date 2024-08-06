package trungndph39729.fpoly.assignment.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.project1762.Helper.ManagmentCart
import trungndph39729.fpoly.assignment.Component.ItemCart
import trungndph39729.fpoly.assignment.Component.MyButton
import trungndph39729.fpoly.assignment.R
import trungndph39729.fpoly.assignment.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController) {
    var promoCode by rememberSaveable {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val triggerRecomposition = remember { mutableStateOf(false) }

    val managerCart: ManagmentCart = remember {
        ManagmentCart(context = context) {
            triggerRecomposition.value = !triggerRecomposition.value
        }
    }
    var cartItems = remember { mutableStateListOf(*managerCart.getListCart().toTypedArray()) }

    LaunchedEffect(Unit) {
        if (triggerRecomposition.value) {
            cartItems.clear()
            cartItems.addAll(managerCart.getListCart())
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "My cart",
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
                }, modifier = Modifier.padding(horizontal = 20.dp)

            )
        },
        containerColor = Color(0xffffffff),
    ) {
        it
        if (managerCart.getListCart().size == 0) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Cart is empty",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = Color(0xff303030),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 20.dp)
                ) {
                    itemsIndexed(cartItems) { index, item ->
                        ItemCart(
                            product = item,
                            managerCart = managerCart,
                            cartItems = cartItems
                        )
                        if (index < cartItems.size - 1) {
                            Divider(
                                color = Color(0xfff0f0f0), // Màu của dòng kẻ
                                thickness = 1.dp // Chiều cao của dòng kẻ
                            )
                        }

                    }
                }

                Column(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(44.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TextField(
                                value = promoCode,
                                onValueChange = { promoCode = it },
                                label = {
                                    Text(
                                        "Enter your promo code",
                                        fontWeight = FontWeight.W400,
                                        fontSize = 16.sp,
                                        color = Color(0xff999999)
                                    )
                                },
                                modifier = Modifier.height(44.dp),
                                shape = RoundedCornerShape(10.dp),
                                maxLines = 1,
                                colors = TextFieldDefaults.colors(
                                    unfocusedLabelColor = Color(0xff303030),
                                    unfocusedContainerColor = Color.White,
                                    focusedLabelColor = Color(0xff303030),
                                    focusedContainerColor = Color.White,
                                    cursorColor = Color(0xff303030),
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),

                                )

                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .background(
                                        Color(0xff303030),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .clickable { },
                                contentAlignment = Alignment.Center,

                                ) {
                                Image(
                                    painter = painterResource(id = R.drawable.right_arrow),
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),

                                    )
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total:",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W700,
                            color = Color(0xff808080)
                        )
                        Text(
                            text = "$ ${managerCart.getTotalFee()}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W700,
                            color = Color(0xff303030)
                        )
                    }

                    MyButton(
                        text = "Check out",
                        onTap = { navController.navigate(Screens.CheckOutScreen.route) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    )
                }


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    var navController = rememberNavController()
    CartScreen(navController = navController)
}
