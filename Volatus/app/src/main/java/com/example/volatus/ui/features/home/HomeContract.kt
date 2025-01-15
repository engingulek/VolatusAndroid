package com.example.volatus.ui.features.home

import com.example.volatus.R
import com.example.volatus.ui.theme.ImageType
import com.example.volatus.ui.theme.worldMap
import com.example.volatus.ui.theme.swapLocationIcon

object HomeContract {
    //UiState
    data class UiState(
        var backImage: ImageType = worldMap,

        var swapIcon: ImageType = swapLocationIcon,

        var searchButtonTitle: Int = R.string.search,

        val fromTitle: Int = R.string.from,
        val toTitle: Int = R.string.to,

        val departureTitle: Int = R.string.departure,
        val returnTitle: Int = R.string.returnTitle,

        var returnVisible: Boolean = true,

        var passengerTitle: Int = R.string.passenger,

        )


    // TripTypeState
    data class TripTypeState(
        val oneWayTitle: Int = R.string.oneway,
        val roundedTitle: Int = R.string.roundTrip,
        val oneWayTripeType: TripeTypeData =
            TripeTypeData(
            contentColor = R.color.white,
            containerColor = R.color.red
            ),
        val roundedTripeType: TripeTypeData =
            TripeTypeData(
                contentColor = R.color.red,
                containerColor = R.color.white
            )
    )

    data class TripeTypeData(
        var contentColor: Int,
        var containerColor: Int
    )

    //UiAction
    sealed interface UiAction {
        data object OnClickOneWay : UiAction
        data object OnClickRoundedTrip : UiAction
    }
}