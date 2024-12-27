package com.example.volatus.ui.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.volatus.ui.features.home.components.LocationComponent
import com.example.volatus.ui.features.home.components.PassengerComponent
import com.example.volatus.ui.features.home.components.SearchButtonComponent

import com.example.volatus.ui.features.home.components.TimeComponent
import com.example.volatus.ui.features.home.components.TripTypeComponent

@Composable
fun HomeScreen(
    viewModel:HomeViewModelInterface = HomeViewModel()
) {
    val state = viewModel.uiState.value
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(state.backImage.image),
                contentScale = ContentScale.FillWidth,
                contentDescription = stringResource(state.backImage.contentDescription),
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

                    TripTypeComponent(type = state.oneWayTripeType)
                    TripTypeComponent(type = state.roundedTripeType)
                }

                LocationComponent(type = state.fromLocationComponent)

                Image(
                    painter = painterResource(state.swapIcon.image),
                    contentDescription = stringResource(state.swapIcon.contentDescription),
                    modifier = Modifier.size(40.dp)
                )

                LocationComponent(type = state.toLocationComponent)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                    if (!state.returnTimeComponentData.visible) Arrangement.SpaceBetween else
                    Arrangement.Center

                ) {
                    TimeComponent(state.departureTimeComponent)
                    if (!state.returnTimeComponentData.visible)
                    TimeComponent(state.returnTimeComponentData)
                }

                PassengerComponent(type = state.passengerComponentData)

                SearchButtonComponent(title = state.searchButtonTitle)
            }
        }

}






@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
