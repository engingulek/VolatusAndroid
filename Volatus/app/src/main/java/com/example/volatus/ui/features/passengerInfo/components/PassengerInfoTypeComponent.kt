package com.example.volatus.ui.features.passengerInfo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volatus.ui.features.passengerInfo.InfoShow
import com.example.volatus.ui.theme.downArrow
import com.example.volatus.ui.theme.upArrow

@Composable
fun PassengerInfoTypeComponent(
    item: InfoShow,
    onTap:() -> Unit
) {
    Row(modifier = Modifier
        .background(Color.White)
        .fillMaxWidth().padding(10.dp)
        .clickable(onClick = onTap)
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("${item.index + 1}. ${item.passengerTitle}",
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold)
        )
        Icon(
            imageVector = if(item.showState) upArrow.image
            else downArrow.image,
            modifier = Modifier.size(30.dp),
            contentDescription = if(item.showState)
                stringResource(upArrow.contentDescription)
            else stringResource(downArrow.contentDescription),

            )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PassengerInfoTypeComponentPreview() {
    PassengerInfoTypeComponent(
        item = InfoShow(1,"Adult",false),
        onTap = {})
}