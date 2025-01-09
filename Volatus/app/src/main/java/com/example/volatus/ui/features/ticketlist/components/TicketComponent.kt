package com.example.volatus.ui.features.ticketlist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volatus.R
import com.example.volatus.ui.theme.hourIcon
import com.example.volatus.ui.theme.minusIcon

@Composable
fun TicketComponent(onTap:() -> Unit) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .clickable  (onClick = onTap )
    ) {
        // Head Start
        Row(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row( horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Image(
                    modifier = Modifier
                        .size(40.dp).clip(CircleShape).padding(),
                    painter = painterResource(R.drawable.test_icon,),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                )
                Column {
                    Text("Airlines Name",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold)
                    )
                    Text("Plane Type",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.Gray)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Icon( imageVector = hourIcon.image,
                    contentDescription = stringResource(minusIcon.contentDescription),
                    tint = hourIcon.color,
                    modifier = Modifier.size(20.dp))
                Text("2h:30m")
            }
        }// head finish

        //middle Start
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text("09:15", style = TextStyle(fontSize = 35.sp))
                Text("Code - City Name")
            }

            Column(horizontalAlignment = Alignment.End) {
                Text("12:15", style = TextStyle(fontSize = 35.sp))
                Text("Code - City Name")
            }
        }// middle Finish
        HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.End) {
            Text("TRY 1500",
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

    }
}