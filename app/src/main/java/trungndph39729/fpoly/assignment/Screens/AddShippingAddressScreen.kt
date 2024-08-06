package trungndph39729.fpoly.assignment.Screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import trungndph39729.fpoly.assignment.Component.CustomOutlinedTextField
import trungndph39729.fpoly.assignment.Component.MyButton
import trungndph39729.fpoly.assignment.Models.UserAddress
import trungndph39729.fpoly.assignment.Models.UserRequest
import trungndph39729.fpoly.assignment.R
import trungndph39729.fpoly.assignment.login_screen.SignInViewModel
import trungndph39729.fpoly.assignment.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddShippingAddressScreen(navController: NavController,signInViewModel: SignInViewModel) {
    var fullName by rememberSaveable {
        mutableStateOf("")
    }
    var address by rememberSaveable {
        mutableStateOf("")
    }

    var addressCity by rememberSaveable {
        mutableStateOf("")
    }

    var phoneNumber by rememberSaveable {
        mutableStateOf("")
    }

    val user by signInViewModel.user.observeAsState()

    val context = LocalContext.current




    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    "Add shipping address",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.fillMaxWidth()
                )
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xffffffff)
            ), navigationIcon = {
                Image(painterResource(id = R.drawable.back_arrow),
                    contentDescription = null,
                    Modifier
                        .size(20.dp)
                        .clickable {
                            navController.popBackStack()
                        })
            }, modifier = Modifier.padding(horizontal = 20.dp), actions = {}

            )
        },
        containerColor = Color(0xffffffff),
    ) {
        it
        Column(
            Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .background(Color.White)
            ) {
                CustomOutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = "Full name",
                    placeholder = "Ex: John Doe"
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomOutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = "Phone Number",
                    placeholder = "Ex: 0-123-456-789"
                )

                Spacer(modifier = Modifier.height(20.dp))
                CustomOutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = "Street, Lankmark, House No",
                    placeholder = "Ex: 25 Robert Latouche Street"
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomOutlinedTextField(
                    value = addressCity,
                    onValueChange = { addressCity = it },
                    label = "City, Distric, Commune",
                    placeholder = "Ex: Ha Noi, Ba Dinh, Cau Giay"
                )


            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 20.dp)
            ) {
                MyButton(
                    text = "SAVE ADDRESS",
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(),
                    onTap = {
                        signInViewModel.updateProfile(
                            user!!._id, UserRequest(
                            phoneNo = phoneNumber,
                            addresses = listOf(
                             UserAddress(
                                 name = fullName,
                                    address = address,
                                    city = addressCity,
                                    phoneNo = phoneNumber,
                                    isDefault = false
                                )
                            )
                        ))
                        Toast.makeText(context, "Address added", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    })
            }

        }

    }

}

