package trungndph39729.fpoly.assignment.Screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import trungndph39729.fpoly.assignment.navigation.Screens

@Composable
fun IntroScreen(navController: NavController
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = trungndph39729.fpoly.assignment.R.drawable.backgound),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Column(Modifier.width(350.dp)) {
                Text(
                    "MAKE YOUR",
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color(0xff606060),
                    fontWeight = FontWeight.W600

                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "HOME BEAUTIFUL",
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color(0xff303030),
                    fontWeight = FontWeight.W700
                )

            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(45.dp),

                ) {
                Text(
                    text =
                    "The best simple place where you discover most wonderful furnitures and make your home beautiful",
                    maxLines = 3,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color(0xff808080),
                    fontWeight = FontWeight.W400,
                    lineHeight = 35.sp,
                    textAlign = TextAlign.Justify

                )
            }
            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Screens.SignInScreen.route)
                },
                modifier = Modifier
                    .width(160.dp)
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff242424),
                ),
                shape = RoundedCornerShape(5.dp)

            ) {
                Text(
                    text = "Get Started",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600,
                    lineHeight = 23.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif

                )
            }

        }

    }
}
