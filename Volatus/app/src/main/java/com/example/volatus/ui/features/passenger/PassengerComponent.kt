package com.example.volatus.ui.features.passenger

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volatus.ui.theme.addIcon
import com.example.volatus.ui.theme.minusIcon

@Composable
fun PassengerComponent(
    passenger: Passenger,
    minusAction:() -> Unit,
    addAction:()->Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth().padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(passenger.title,
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                stringResource(passenger.ageSpaceTitle),
                style = TextStyle(fontSize = 18.sp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick =  minusAction,
                enabled = !passenger.minusButtonStatus

            ) {
                Icon(
                    imageVector = minusIcon.image,
                    contentDescription = stringResource(minusIcon.contentDescription),
                    tint = if (passenger.minusButtonStatus) minusIcon.enabledColor else minusIcon.color,
                    modifier = Modifier.size(35.dp)
                )
            }

            Text(
                text = "${passenger.count}",
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            )

            IconButton(
                onClick = addAction
            ) {
                Icon(
                    imageVector = addIcon.image,
                    contentDescription = stringResource(addIcon.contentDescription),
                    tint = addIcon.color,
                    modifier = Modifier.size(35.dp)
                )
            }

        }

    }
}