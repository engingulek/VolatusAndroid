package com.example.volatus.ui.features

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volatus.ui.theme.datePicker

@Composable
fun TestScreen() {
    Row(
        modifier = Modifier
            .background(Color.White)
            .border(2.dp, Color.LightGray, RoundedCornerShape(10.dp))
            .padding(16.dp) ,// Adjust padding for the content inside
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(25.dp),
            imageVector = datePicker.image,
            contentDescription =
            stringResource(datePicker.contentDescription))
        Text("11/12/2022", style = TextStyle(
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold))

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TestScreenPreview() {
    TestScreen()
}