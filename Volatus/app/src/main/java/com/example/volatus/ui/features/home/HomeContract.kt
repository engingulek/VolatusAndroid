package com.example.volatus.ui.features.home

import androidx.annotation.Nullable
import androidx.compose.ui.res.stringResource
import com.example.volatus.R
import com.example.volatus.ui.features.airtportList.Airport
import com.example.volatus.ui.features.passenger.Passenger
import com.example.volatus.ui.theme.ImageType
import com.example.volatus.ui.theme.worldMap

import com.example.volatus.ui.theme.swapLocationIcon
import java.time.LocalDate

object HomeContract {
    data class UiState(
        var backImage:ImageType= worldMap,

        var swapIcon:ImageType= swapLocationIcon,

        var searchButtonTitle:Int =  R.string.search,

        val fromTitle:Int = R.string.from,
        val toTitle:Int = R.string.to,

        val departureTitle:Int =  R.string.departure,
        val returnTitle:Int = R.string.returnTitle ,

        var returnVisible:Boolean = true,

        var passengerTitle:Int = R.string.passenger,

    )




    data class  TripTypeState(
        val oneWayTitle : Int = R.string.oneway,
        val roundedTitle:Int = R.string.roundTrip,
        val oneWayTripeType: TripeTypeData = TripeTypeData(

            contentColor = R.color.white,
            containerColor = R.color.red
        ),
        val roundedTripeType: TripeTypeData =
            TripeTypeData(
                contentColor = R.color.red,
                containerColor = R.color.white
            )
    )

    data class  TripeTypeData(

        var contentColor:Int,
        var containerColor:Int
        )


    sealed interface UiAction{

        data object OnClickOneWay :UiAction
        data object OnClickRoundedTrip:UiAction



    }
}