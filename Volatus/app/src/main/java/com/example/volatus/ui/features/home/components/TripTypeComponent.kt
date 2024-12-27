package com.example.volatus.ui.features.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volatus.R
import com.example.volatus.ui.features.home.HomeContract

@Composable
fun TripTypeComponent(type:HomeContract.TripeType) {
    Button(
        onClick = { },
        modifier = Modifier
            .height(50.dp)
            .border(
                width = 1.dp,
                color = Color.Red,
                shape = RoundedCornerShape(8.dp)
            ) .background(
                color = colorResource(type.containerColor),
                shape = RoundedCornerShape(8.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor =  colorResource(type.containerColor),
            contentColor = colorResource(type.contentColor)

        )
    ) {
        Text(
            stringResource(type.title),
            style = TextStyle(fontSize = 20.sp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TripTypeComponentPreview() {
    TripTypeComponent(type = HomeContract.TripeType(R.string.oneway,R.color.white,R.color.red))
}