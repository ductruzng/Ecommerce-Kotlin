package trungndph39729.fpoly.assignment.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import trungndph39729.fpoly.assignment.Component.MyButton
import trungndph39729.fpoly.assignment.R
import trungndph39729.fpoly.assignment.navigation.Screens

@Composable
fun SuccessScreen(navController: NavController) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(id = R.drawable.success),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Box(modifier = Modifier.padding(20.dp)) {
            MyButton(
                text = "Track your orders",
                onTap = {
                    navController.popBackStack()
                    navController.navigate(Screens.HistoryOrderScreen.route) },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
            )
        }
        Button(
            onClick =
            {
                navController.popBackStack()
                navController.navigate(Screens.NavBar.route)
            },
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .border(1.dp, Color(0xff303030), RoundedCornerShape(12.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffffffff))
        ) {
            Text(
                "BACK TO HOME", color = Color(0xff303030), fontSize = 18.sp,
                fontWeight = FontWeight.W500,
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SuccessScreenPreview() {
    var navController = rememberNavController()
    SuccessScreen(navController = navController)
}