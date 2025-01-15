package com.example.volatus.ui.features.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volatus.R
import com.example.volatus.utils.extensions.customBorderBackground

@Composable
fun LocationComponent(
    title:Int,
    location:String,
    navigation:() -> Unit
    ){
        Column( modifier = Modifier
            .fillMaxWidth()
            .customBorderBackground(padding = 16.dp,navigation)

         ) {
            Text(stringResource(title))
            Text(location,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold)
            )
        }
    }

@Preview(showBackground = true)
@Composable
fun LocationComponentPreview() {
    LocationComponent(title = R.string.from, location = "Choose", navigation = {})
}
