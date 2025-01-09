package com.example.volatus.ui.features.airtportList.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volatus.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SearchComponent(
    placeholder:Int,
    searchAction:(String) -> Unit
) {
     var query by remember { mutableStateOf("") }
    TextField(
        value = query,
        onValueChange = {
            query = it
            searchAction(it)
                        },
        placeholder = {
            Text(
                stringResource(placeholder),
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.LightGray,
                )
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(2.dp, Color.Red),
                RoundedCornerShape(10.dp)
            )
            .background(Color.White, RoundedCornerShape(10.dp))


        ,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SearchVComponentPreview() {
    SearchComponent(R.string.searchPlaceholder, searchAction = {})

}