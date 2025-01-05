package com.example.volatus.ui.features.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.volatus.R
import com.example.volatus.hilt.shared.SharedContract
import com.example.volatus.ui.features.home.HomeContract

@Composable
fun PassengerComponent(
    title:Int,
    state:SharedContract.PassengerState,
    navigation:() -> Unit
) {
    Column( modifier = Modifier
        .fillMaxWidth()

        .border(
            width = 1.dp,
            color = Color.LightGray,
            shape = RoundedCornerShape(16.dp)
        )
        .background(color = Color.White)
        .padding(16.dp)
        .clickable(onClick = navigation)
    ) {
        Text(stringResource(R.string.passenger))
        Text(state.passengerText,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold)
        )
    }
}

/*@Preview(showBackground = true)
@Composable
fun PassengerComponentPreview() {
    PassengerComponent(state = , navigation = {})

}*/