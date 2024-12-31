package com.example.volatus.ui.features.airtportList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.volatus.ui.features.airtportList.components.AirportList
import com.example.volatus.ui.features.airtportList.components.SearchComponent


@Composable
fun AirportListScreen() {

    Column(modifier = Modifier.fillMaxSize()
        .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        SearchComponent()
        AirportList()

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AirportListScreenPreview() {
    AirportListScreen()


}