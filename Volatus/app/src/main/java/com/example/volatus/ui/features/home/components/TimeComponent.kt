package com.example.volatus.ui.features.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import com.example.volatus.ui.features.home.HomeContract
import com.example.volatus.ui.theme.BackgroundColor
import com.example.volatus.ui.theme.borderColor

@Composable
fun TimeComponent(
    title:Int,
    dateText:String,
    navigation:() -> Unit) {

        Column( modifier = Modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .background(color = BackgroundColor)
            .padding(10.dp)
            .clickable(onClick = navigation)
        ) {
            Text(stringResource(title))
            Text(dateText,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold)
            )

        }
}


@Preview(showBackground = true)
@Composable
fun TimeComponentPreview() {
        TimeComponent(title = R.string.departure, dateText = "January 01,2025", navigation = {})

}
