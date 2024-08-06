package trungndph39729.fpoly.assignment.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import trungndph39729.fpoly.assignment.Models.User
import trungndph39729.fpoly.assignment.Models.UserAddress
import trungndph39729.fpoly.assignment.R

@Composable
fun ItemAddress(
    userAddress: UserAddress
) {
    var checked by remember { mutableStateOf(false) }
        Column(
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = checked,

                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xff242424),
                    ),
                    onCheckedChange = { checked = it },
                )

                Text(
                    text = "Use as the shipping address",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400,
                    color = Color(0xff242424)
                )
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 15.dp, bottom = 25.dp),


                ) {
                Column {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, top = 15.dp, bottom = 15.dp, end = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Text(
                            text = userAddress.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W700,
                            color = Color(0xff242424)
                        )
                        Image(
                            painterResource(id = R.drawable.edit_btn), contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )

                    }
                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                            .background(color = Color(0xfff0f0f0))
                    )
                    Text(
                        "${userAddress.address}, ${userAddress.city}}",
                        color = Color(0xff808080),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        modifier = Modifier.padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 10.dp,
                            bottom = 15.dp
                        )
                    )
                }


            }
        }
    }
