package trungndph39729.fpoly.assignment.signup_screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import trungndph39729.fpoly.assignment.Component.MyButton
import trungndph39729.fpoly.assignment.Models.UserRequest
import trungndph39729.fpoly.assignment.R

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel
) {

    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var name by rememberSaveable {
        mutableStateOf("")
    }

    var showPassword = rememberSaveable { mutableStateOf(false) }
    var showConfirmPassword = rememberSaveable { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signUpState.collectAsState(initial = null)
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(105.dp)
                    .height(1.dp)
                    .background(Color(0xffBDBDBD))
            )
            Spacer(modifier = Modifier.width(20.dp))
            Image(
                painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                Modifier
                    .width(63.dp)
                    .height(63.dp)

            )
            Spacer(modifier = Modifier.width(20.dp))

            Box(
                modifier = Modifier
                    .width(105.dp)
                    .height(1.dp)
                    .background(Color(0xffBDBDBD))

            )
        }

        Column(
            Modifier
                .width(350.dp)
                .padding(30.dp)
        ) {

            Text(
                "WELCOME!",
                fontSize = 24.sp,
                fontFamily = FontFamily.Serif,
                color = Color(0xff303030),
                fontWeight = FontWeight.W700,
            )

        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 30.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        shadowElevation = 10.dp.toPx()
                        shape = RectangleShape
                        clip = true
                    }
                    .background(Color.White)
                    .padding(vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp)
                ) {

                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Full Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        maxLines = 1,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            disabledLabelColor = Color(0xff303030),
                            unfocusedLabelColor = Color(0xff303030),
                            focusedLabelColor = Color(0xff303030),
                            focusedIndicatorColor = Color(0xff303030),
                            cursorColor = Color(0xff303030)


                        )

                    )

                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        maxLines = 1,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            disabledLabelColor = Color(0xff303030),
                            unfocusedLabelColor = Color(0xff303030),
                            focusedLabelColor = Color(0xff303030),
                            focusedIndicatorColor = Color(0xff303030),
                            cursorColor = Color(0xff303030)


                        )

                    )
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        visualTransformation = if (showPassword.value) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            IconButton(
                                onClick = { showPassword.value = !showPassword.value }
                            ) {
                                Icon(
                                    painter = painterResource(
                                        id = if (showPassword.value) R.drawable.close_eye else R.drawable.eye
                                    ),
                                    modifier = Modifier.size(24.dp),
                                    contentDescription = if (showPassword.value) "Hide Password" else "Show Password"
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        maxLines = 1,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            disabledLabelColor = Color(0xff303030),
                            unfocusedLabelColor = Color(0xff303030),
                            focusedLabelColor = Color(0xff303030),
                            cursorColor = Color(0xff303030),
                            focusedIndicatorColor = Color(0xff303030)

                        )
                    )
                    TextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text("Confirm Password") },
                        visualTransformation = if (showConfirmPassword.value) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            IconButton(
                                onClick = { showConfirmPassword.value = !showConfirmPassword.value }
                            ) {
                                Icon(
                                    painter = painterResource(
                                        id = if (showConfirmPassword.value) R.drawable.close_eye else R.drawable.eye
                                    ),
                                    modifier = Modifier.size(24.dp),
                                    contentDescription = if (showConfirmPassword.value) "Hide Password" else "Show Password"
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        maxLines = 1,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            disabledLabelColor = Color(0xff303030),
                            unfocusedLabelColor = Color(0xff303030),
                            focusedLabelColor = Color(0xff303030),
                            focusedIndicatorColor = Color(0xff303030),
                            cursorColor = Color(0xff303030)

                        )
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                ) {
                    MyButton(
                        text = "SIGN UP",
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth(),
                        onTap = {
                            if (email.isEmpty() || password.isEmpty() || name.isEmpty() || confirmPassword.isEmpty()) {
                                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                                return@MyButton
                            }
                            if (password != confirmPassword) {
                                Toast.makeText(context, "Password does not match", Toast.LENGTH_SHORT).show()
                                return@MyButton
                            }

                            scope.launch {
                                viewModel.createUser(
                                    UserRequest(
                                        email = email,
                                        name = name,
                                        password = confirmPassword
                                    )
                                )
                            }
                        })
                }

                Row(
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                        .clickable { navController.popBackStack() },
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Already have account? ",
                        color = Color(0xff808080),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                        fontFamily = FontFamily.SansSerif,
                    )
                    Text(
                        text = "SIGN IN",
                        color = Color(0xff303030),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily.SansSerif,
                    )

                }
            }

            LaunchedEffect(key1 = state.value?.isSuccess) {
                scope.launch {
                    if (state.value?.isSuccess?.isNotEmpty() == true) {
                        val success = state.value?.isSuccess
                        name = ""
                        email = ""
                        password = ""
                        confirmPassword = ""
                        Toast.makeText(context, "${success}", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                }
            }
            LaunchedEffect(key1 = state.value?.isError) {
                scope.launch {
                    if (state.value?.isError?.isNotEmpty() == true) {
                        val error = state.value?.isError
                        Toast.makeText(context, "${error}+", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}
