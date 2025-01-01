package com.example.volatus.ui.features.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.runtime.getValue
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
    viewModel:HomeViewModelInterface,
    navigationToAirportList:(Boolean) -> Unit,

) {
    val state by viewModel.uiState.collectAsState()
    val dateState by viewModel.dateState.collectAsState()
    val tripState  by viewModel.tripState.collectAsState()
    val locationState by viewModel.locationState.collectAsState()
    val passengerState by viewModel.passengerState.collectAsState()

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

                    val oneWay: () -> Unit = {
                       viewModel.onAction(HomeContract.UiAction.OnClickOneWay)
                    }
                    val roundedTrip :  () -> Unit = {
                    viewModel.onAction(HomeContract.UiAction.OnClickRoundedTrip)
                }

                    TripTypeComponent(
                        type = tripState.oneWayTripeType,
                        title = tripState.oneWayTitle,
                        onClick = oneWay)

                   TripTypeComponent(
                        type = tripState.roundedTripeType,
                       title = tripState.roundedTitle,
                        onClick = roundedTrip)
                }

                LocationComponent(
                    title = locationState.fromTitle,
                    location = locationState.fromLocation,
                    navigation = {navigationToAirportList(true)})

                Image(
                    painter = painterResource(state.swapIcon.image),
                    contentDescription = stringResource(state.swapIcon.contentDescription),
                    modifier = Modifier.size(40.dp)
                )

                LocationComponent(
                    title = locationState.toTitle,
                    location = locationState.toLocation,
                    navigation = {navigationToAirportList(false)}
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                    if (!dateState.returnVisible) Arrangement.SpaceBetween
                    else Arrangement.Center

                ) {
                    TimeComponent(dateState.departureTitle)
                    if (!dateState.returnVisible)
                        TimeComponent(dateState.returnTitle)
                }

                PassengerComponent(state = passengerState)

                SearchButtonComponent(title = state.searchButtonTitle)
            }
        }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(viewModel = HomeViewModel(),navigationToAirportList = {})
}
