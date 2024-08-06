package trungndph39729.fpoly.assignment.Screens

import android.util.Log
import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import trungndph39729.fpoly.assignment.Component.MenuItem
import trungndph39729.fpoly.assignment.Component.OrderCard
import trungndph39729.fpoly.assignment.Models.Order
import trungndph39729.fpoly.assignment.Models.OrderRequest
import trungndph39729.fpoly.assignment.Models.UserAddress
import trungndph39729.fpoly.assignment.R
import trungndph39729.fpoly.assignment.login_screen.SignInViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryOrderScreen(navController: NavController,signInViewModel: SignInViewModel) {

    val user by signInViewModel.user.observeAsState()

    val userOrder by signInViewModel.orders.observeAsState()

   LaunchedEffect(key1 = Unit) {
       signInViewModel.getOrders(user?._id?:"")
   }

    val orders = userOrder ?: emptyList()


    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    "My order",
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.White)
        ) {
//            DeliveryStatus()
            LazyColumn(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(top= 20.dp)
            ) {
                items(orders) {
//        MenuItem(it)
                    OrderCard(it) {

                    }
                }
            }
        }
    }
}

@Composable
fun DeliveryStatus() {
    val statusList = listOf("Delivered", "Processing", "Cancelled")
    val selectedIndex = remember { mutableStateOf(0) }
    val indicatorOffset = remember { androidx.compose.animation.core.Animatable(0f) }
    LaunchedEffect(selectedIndex.value) {
        indicatorOffset.animateTo(selectedIndex.value.toFloat())
    }
    Box(
        Modifier
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            statusList.forEachIndexed { index, status ->
                val isSelected = selectedIndex.value == index
                val colorSelected =
                    if (isSelected) Color(0xff242424) else Color(0xff999999)

                val fontWeightSelected =
                    if (isSelected) FontWeight.W700 else FontWeight.W400
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { selectedIndex.value = index }
                ) {
                    Text(
                        text = status,
                        color = colorSelected,
                        fontSize = 18.sp,
                        fontWeight = fontWeightSelected,

                        )
                    Spacer(modifier = Modifier.height(10.dp))
                    if (isSelected) {
                        Box(
                            modifier = Modifier
                                .height(4.dp)
                                .width(40.dp)
                                .background(Color(0xff242424), shape = RoundedCornerShape(4.dp))
                        )
                    }
                }
            }
        }
    }

}
