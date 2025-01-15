package com.example.volatus.ui.features.date.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volatus.ui.features.date.DateValueType
import com.example.volatus.utils.extensions.getDateCardColor
import com.example.volatus.utils.extensions.getDateColor


@Composable
fun DayComponent(
    day: Pair<DateValueType, Int?>,
    onTapped:() -> Unit
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable(
                onClick = onTapped,
                enabled = day.first != DateValueType.DISABLE
            ),

        colors = CardDefaults.cardColors(
            containerColor = day.first.getDateCardColor()
        )
    ) {

        Text(
            text = "${day.second}",
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            style = TextStyle(
                color = day.first.getDateColor(),
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

        )
    }
}