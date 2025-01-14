package com.example.volatus.utils.conponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.volatus.ui.theme.errorColor

@Composable
fun MessageComponent(message:Int) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            stringResource(message),
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 20.sp,
                color = errorColor,
                fontWeight = FontWeight.SemiBold)

        )
    }
}