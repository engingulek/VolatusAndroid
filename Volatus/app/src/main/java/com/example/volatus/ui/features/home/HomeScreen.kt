package com.example.volatus.ui.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volatus.R
import com.example.volatus.ui.features.home.components.LocationComponent
import com.example.volatus.ui.features.home.components.PassengerComponent
import com.example.volatus.ui.features.home.components.SearchButtonComponent

import com.example.volatus.ui.features.home.components.TimeComponent
import com.example.volatus.ui.features.home.components.TripTypeComponent
import com.example.volatus.ui.theme.swapIcon
import com.example.volatus.ui.theme.worldMap

@Composable
fun HomeScreen() {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(worldMap.image),
                contentScale = ContentScale.FillWidth,
                contentDescription = stringResource(worldMap.contentDescription),
                modifier = Modifier.fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(
                        bottomStart = 10.dp, bottomEnd = 10.dp))
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .padding(top = 100.dp)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 20.dp, horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly) {

                    TripTypeComponent(R.string.oneway,
                        contentColor = Color.White,
                        containerColor = Color.Red)
                    TripTypeComponent(R.string.roundTrip,
                        contentColor = Color.Red,
                        containerColor = Color.White)
                }

                LocationComponent(R.string.from)

                Image(
                    painter = painterResource(swapIcon.image),
                    contentDescription = stringResource(swapIcon.contentDescription),
                    modifier = Modifier.size(40.dp)
                )

                LocationComponent(R.string.to)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {

                    TimeComponent(R.string.departure)

                    TimeComponent(R.string.returnTitle)
                }

                PassengerComponent()

                SearchButtonComponent()
            }
        }

}






@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
