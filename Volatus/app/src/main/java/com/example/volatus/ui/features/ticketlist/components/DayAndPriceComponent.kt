package com.example.volatus.ui.features.ticketlist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volatus.ui.features.ticketlist.departureTicketList.DayAndPrice
import com.example.volatus.utils.extensions.FormaterType
import com.example.volatus.utils.extensions.formatter

@Composable
fun DayAndPriceComponent(
    dayAndPrice : DayAndPrice,
    onTap:() -> Unit
) {
    Column(
        Modifier.clickable(onClick = onTap),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(
            text = dayAndPrice.date.formatter(FormaterType.TypeFour),
            style = TextStyle(fontSize = 20.sp, color = Color.Gray),
            textAlign = TextAlign.Center,
            color = Color( dayAndPrice.selectedStateColor)
        )
        Text(
            text = "TRY ${dayAndPrice.price}",
            style = TextStyle(fontSize = 15.sp,color = Color.Gray),
            textAlign = TextAlign.Center,
            color = Color( dayAndPrice.selectedStateColor)
        )
        Divider(
            modifier = Modifier
                .width(65.dp)
                .height(2.dp),
            color = Color( dayAndPrice.selectedStateColor)
        )
    }
}