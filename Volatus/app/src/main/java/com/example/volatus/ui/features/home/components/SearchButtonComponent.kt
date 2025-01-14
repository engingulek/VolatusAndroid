package com.example.volatus.ui.features.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volatus.R
import com.example.volatus.ui.theme.primaryTextColor
import com.example.volatus.ui.theme.secondaryBackColor

@Composable
fun SearchButtonComponent(
    title:Int,
    enable:Boolean,
    onClicked:()-> Unit
) {
    Button(
        onClick = onClicked,
        enabled = enable,
        modifier = Modifier.fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = secondaryBackColor,
            contentColor = primaryTextColor
        )
    ) {
        Text(
            stringResource(title),
            style = TextStyle(fontSize = 20.sp)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun SearchButtonComponentPreview() {
    SearchButtonComponent(title = R.string.search, enable = false, onClicked = {})
}
