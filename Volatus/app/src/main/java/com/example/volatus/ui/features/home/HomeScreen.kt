package com.example.volatus.ui.features.home

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.runtime.getValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.volatus.shared.SharedContract
import com.example.volatus.shared.SharedModel
import com.example.volatus.ui.features.home.components.LocationComponent
import com.example.volatus.ui.features.home.components.PassengerComponent
import com.example.volatus.ui.features.home.components.SearchButtonComponent

import com.example.volatus.ui.features.home.components.TimeComponent
import com.example.volatus.ui.features.home.components.TripTypeComponent
import com.example.volatus.ui.theme.BackgroundColor
import com.example.volatus.ui.theme.borderColor

@Composable
fun HomeScreen(
    viewModel:HomeViewModelInterface,
    sharedModel : SharedModel,
    navigationToAirportList:(Boolean) -> Unit,
    navigationToDateScreen:(Boolean) -> Unit,
    navigationToPassenger:() -> Unit,
    navigationToDepartureTicketList:() -> Unit

) {
    val state by viewModel.uiState.collectAsState()
    val tripState  by viewModel.tripState.collectAsState()
    val airportState by sharedModel.airportUiState.collectAsState()
    val dateState by sharedModel.dateState.collectAsState()
    val passengerState by sharedModel.passengerState.collectAsState()

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(state.backImage.image),
                contentScale = ContentScale.FillWidth,
                contentDescription = stringResource(state.backImage.contentDescription),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(
                            bottomStart = 10.dp, bottomEnd = 10.dp
                        ))
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .padding(top = 100.dp)
                    .border(
                        width = 1.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(
                        color = BackgroundColor,
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
                    //Trips Start
                    TripTypeComponent(
                        type = tripState.oneWayTripeType,
                        title = tripState.oneWayTitle,
                        onClick = {
                            oneWay()
                            sharedModel.onAction(SharedContract.SharedAction.updateReturnState(false))
                        })

                   TripTypeComponent(
                        type = tripState.roundedTripeType,
                       title = tripState.roundedTitle,
                        onClick = {
                            roundedTrip()
                            sharedModel.onAction(SharedContract.SharedAction.updateReturnState(true))
                        })//Trips Finish
                }

                //LocationComponent Start
                LocationComponent(
                    title = state.fromTitle,
                    location = airportState.fromAirportTextString,
                    navigation = {navigationToAirportList(true)})

                Image(
                    painter = painterResource(state.swapIcon.image),
                    contentDescription = stringResource(state.swapIcon.contentDescription),
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            sharedModel.onAction(SharedContract.SharedAction.onTappedSwapIcon)
                        }
                )

                LocationComponent(
                    title = state.toTitle,
                    location = airportState.toAirportText,
                    navigation = {navigationToAirportList(false)}
                )  //LocationComponent Finish

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                    if (!state.returnVisible) Arrangement.SpaceBetween
                    else Arrangement.Center

                ) {
                    TimeComponent(
                        state.departureTitle,
                        dateText = dateState.departureDateText,
                        navigation = {navigationToDateScreen(true)})

                    if (!state.returnVisible)
                        TimeComponent(
                            state.returnTitle,
                            dateText = dateState.returnDateText,
                            navigation = { navigationToDateScreen(false) }
                        )
                }

                PassengerComponent(
                    state = passengerState,
                    navigation = {navigationToPassenger()}
                )

                SearchButtonComponent(
                    title = state.searchButtonTitle,
                    enable = airportState.airportState,
                    onClicked = {navigationToDepartureTicketList()}
                )
            }
        }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        viewModel = HomeViewModel(),
        sharedModel = SharedModel(),
        navigationToAirportList = {},
        navigationToDateScreen = {},
        navigationToPassenger = {}, navigationToDepartureTicketList = {})
}
