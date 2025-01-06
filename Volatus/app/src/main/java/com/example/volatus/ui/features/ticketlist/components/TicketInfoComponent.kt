package com.example.volatus.ui.features.ticketlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TicketInfoComponent(title:String) {
    Column(
        Modifier.fillMaxWidth()
            .padding(vertical = 10.dp)
            .background(Color.White)
            .padding(10.dp)
        ,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(title,
            style = TextStyle(color = Color.Red,fontSize = 20.sp, fontWeight = FontWeight.SemiBold))
        Column( Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text("From City 00:00", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold))
                Text("To City 00:00", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold))
            }

            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text("01/01/2025", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold))
                Text("TRY 800.00", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold))
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun TicketInfoComponentPreview() {
    TicketInfoComponent("Departure Ticket Info")
}