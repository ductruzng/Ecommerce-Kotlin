package trungndph39729.fpoly.assignment.Component

import android.health.connect.datatypes.HeightRecord
import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyButton(text: String, onTap: () -> Unit,modifier: Modifier) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Button(
            onClick =
            onTap,
            modifier = modifier,

            shape = RoundedCornerShape(8.dp),

            colors = ButtonDefaults.buttonColors(containerColor = Color(0xff242424))
        ) {
            Text(
                text, color = Color.White, fontSize = 18.sp,
                fontWeight = FontWeight.W500,
            )
        }
    }

}