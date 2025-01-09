package com.example.volatus.ui.features.passengerInfo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassengerInfoTextFieldComponent(
    value:String,
    label:String,
    error:Pair<Int,Boolean>,
    keyboardOptions: KeyboardOptions,
    onChange:(newText:String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TextField(
            value = value,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray.copy(alpha = 0.5f),
                errorIndicatorColor = Color.Red,
                cursorColor = Color.Black
            ),
            onValueChange = { newText ->
                onChange(newText)
                            },
            isError = error.second,
            label = { Text(label) },
            singleLine = true,
            maxLines = 1,
            keyboardOptions = keyboardOptions
        )
        Text(stringResource(error.first), style = TextStyle(fontSize = 20.sp, color = Color.Red))
    }
}