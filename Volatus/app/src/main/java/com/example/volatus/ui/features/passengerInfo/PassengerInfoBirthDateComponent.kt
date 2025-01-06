package com.example.volatus.ui.features.passengerInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volatus.ui.theme.datePicker
import com.example.volatus.utils.FormaterType
import com.example.volatus.utils.formatter

@Composable
fun PassengerInfoBirthDateComponent(
    date:String,
    errorState:Pair<Int,Boolean>,
    onTap:() -> Unit
) {
    Row(
        modifier = Modifier
            .background(Color.White)
            .border(2.dp, Color.LightGray, RoundedCornerShape(10.dp))
            .padding(16.dp).clickable(onClick = onTap) ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(25.dp),
            imageVector = datePicker.image,
            contentDescription =
            stringResource(datePicker.contentDescription)
        )
        Text(text = date, style = TextStyle(
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold)
        )

    }
    if (errorState.second)
        Text(stringResource(errorState.first),
            style = TextStyle(fontSize = 20.sp, color = Color.Red))
}