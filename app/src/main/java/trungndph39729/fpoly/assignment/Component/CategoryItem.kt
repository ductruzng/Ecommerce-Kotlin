package trungndph39729.fpoly.assignment.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import trungndph39729.fpoly.assignment.Models.CategoryModel

@Composable
fun CategoryItem(

    backgroundColor: Color,
    imageColor: Color,
    textColor: Color,
    fontWeight: androidx.compose.ui.text.font.FontWeight,
    category: CategoryModel,
    onTap: () -> Unit


) {
    Column(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .padding(6.dp)
                .background(
                    backgroundColor,
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable {
                    onTap()
                },
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = category.picUrl,
                contentDescription = category.title,
                colorFilter = ColorFilter.tint(imageColor),
                modifier = Modifier
                    .padding(6.dp)
                    .size(20.dp)
            )
        }
        Text(
            text = category.title,
            fontSize = 14.sp,
            color = textColor,
            fontWeight = fontWeight,
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}
