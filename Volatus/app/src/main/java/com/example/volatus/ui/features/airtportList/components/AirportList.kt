package com.example.volatus.ui.features.airtportList.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volatus.ui.features.airtportList.Airport

@Composable
fun AirportList(
    list:List<Airport>,
    selectAirport: (Airport)->Unit,
    onBack:()->Unit) {

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("All Airports",
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
            ),

        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Red)
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            items(list){airport ->
                Column(
                    Modifier.clickable {
                        selectAirport(airport)
                        onBack()
                    },
                    verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text("${airport.city},${airport.country}", style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    )
                    Text("${airport.code}-${airport.name}", style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.5.dp)
                            .background(Color.Gray)
                            .padding()
                    )
                }

            }
        }
    }
}