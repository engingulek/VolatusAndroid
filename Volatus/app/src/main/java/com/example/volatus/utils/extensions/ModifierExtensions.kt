package com.example.volatus.utils.extensions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.volatus.ui.theme.BackgroundColor
import com.example.volatus.ui.theme.borderColor

fun Modifier.customBorderBackground(
    padding: Dp,
    navigation:() -> Unit) : Modifier{
    return this.then(
        Modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .background(color = BackgroundColor)
            .padding(padding)
            .clickable(onClick = navigation)
    )
}

