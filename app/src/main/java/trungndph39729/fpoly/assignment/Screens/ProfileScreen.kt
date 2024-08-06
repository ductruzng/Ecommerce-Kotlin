package trungndph39729.fpoly.assignment.Screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
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
import coil.compose.AsyncImage
import trungndph39729.fpoly.assignment.Component.MenuItem
import trungndph39729.fpoly.assignment.R
import trungndph39729.fpoly.assignment.login_screen.SignInViewModel
import trungndph39729.fpoly.assignment.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    signInViewModel: SignInViewModel
) {

    val user by signInViewModel.user.observeAsState()

    user?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
//        TopAppBar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(id = R.drawable.ri_search),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { },
                    colorFilter = ColorFilter.tint(Color(0xff242424))
                )

                Column(
                    modifier = Modifier
                        .width(120.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Profile",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier.padding(12.dp)
                    )
                }
                Image(
                    painterResource(id = R.drawable.logout),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { }
                )

            }

            LazyColumn(
                modifier = Modifier
                    .wrapContentHeight()
            ) {
                item {
                    Spacer(modifier = Modifier.height(20.dp))
// Information
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .height(80.dp),
                    ) {
                        AsyncImage(
                            model = "https://cdn-icons-png.flaticon.com/128/4322/4322991.png",
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(androidx.compose.foundation.shape.CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            Modifier
                                .fillMaxHeight()
                                .padding(start = 20.dp),
                            verticalArrangement = Arrangement.Center
                        ) {

                            Text(
                                text = it.name!!,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W700,
                                color = Color(0xff303030)
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = it.email,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W400,
                                color = Color(0xff808080),
                                textAlign = TextAlign.Justify
                            )


                        }

                    }
//        Menu
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp)
                    ) {
                        MenuItem(
                            title = "My orders",
                            detail = "Already have ${user!!.orders?.size} orders",
                            onClick = {
                                navController.navigate(Screens.HistoryOrderScreen.route)
                            }
                        )
                        MenuItem(
                            title = "Shipping Addresses",
                            detail = "${user?.addresses?.size} addresses",
                            onClick = {
                                navController.navigate(Screens.ShippingAddressScreen.route)

                            }
                        )
                        MenuItem(
                            title = "Payment Method",
                            detail = "Your have 2 cards",
                            onClick = {
                                Log.d("Payment Method", "Payment Method")
                            }
                        )
                        MenuItem(
                            title = "My reviews",
                            detail = "Reviews for 4 items",
                            onClick = {

                            }
                        )
                        MenuItem(
                            title = "Settings",
                            detail = "Notification, Password, FAQ, Contact",
                            onClick = {

                            }
                        )
                    }
                }
            }
        }
    }
}

