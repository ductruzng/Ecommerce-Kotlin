package trungndph39729.fpoly.assignment.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import trungndph39729.fpoly.assignment.Component.ItemAddress
import trungndph39729.fpoly.assignment.Models.UserAddress
import trungndph39729.fpoly.assignment.R
import trungndph39729.fpoly.assignment.login_screen.SignInViewModel
import trungndph39729.fpoly.assignment.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShippingAddressScreen(navController: NavController,signInViewModel: SignInViewModel) {

    val user by signInViewModel.user.observeAsState()


    val userAddresses = remember {
        mutableStateListOf<UserAddress>().apply {
            user?.addresses?.let { addAll(it) }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    "Shipping address",
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
        floatingActionButton = {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                modifier = Modifier
                    .size(52.dp)
                    .background(Color.White, shape = CircleShape),

                ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        Modifier
                            .size(24.dp)
                            .clickable {
                                navController.navigate(Screens.AddShippingAddressScreen.route)
                            },
                        tint = Color(0xff242424),
                    )
                }

            }

        }
    ) {
        it
        Column(
            Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(
                modifier = Modifier.padding(it)
            ) {
                items(userAddresses) {
                    ItemAddress(it)
                }
            }
        }

    }
}

