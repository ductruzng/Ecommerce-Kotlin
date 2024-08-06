package trungndph39729.fpoly.assignment.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import trungndph39729.fpoly.assignment.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuItem(
    title: String,
    detail: String,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 15.dp),
        onClick = onClick

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Column(
                modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 20.dp),

                ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    color = Color(0xff242424),
                    fontWeight = FontWeight.W700
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = detail,
                    fontSize = 12.sp,
                    color = Color(0xff808080),
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Justify
                )
            }
            Image(
                painterResource(id = R.drawable.right_arrow), contentDescription = null,
                modifier = Modifier
                    .size(15.dp)
                    .clickable { onClick() },
                colorFilter = ColorFilter.tint(Color(0xff242424))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuItemPreview() {
    MenuItem(
        title = "Title",
        detail = "Detail",
        onClick = {}
    )
}