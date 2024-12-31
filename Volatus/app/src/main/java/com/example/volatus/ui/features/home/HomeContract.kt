package com.example.volatus.ui.features.home

import androidx.annotation.Nullable
import androidx.compose.ui.res.stringResource
import com.example.volatus.R
import com.example.volatus.ui.features.airtportList.Airport
import com.example.volatus.ui.theme.ImageType
import com.example.volatus.ui.theme.worldMap

import com.example.volatus.ui.theme.swapLocationIcon

object HomeContract {
    data class UiState(
        var backImage:ImageType= worldMap,

        var swapIcon:ImageType= swapLocationIcon,

        var searchButtonTitle:Int =  R.string.search,



    )

    data class DateState(
        val departureTitle:Int =  R.string.departure,
        val returnTitle:Int = R.string.returnTitle ,
        var returnVisible:Boolean = true
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

    data class LocationState(
        val fromTitle:Int = R.string.from,
        val toTitle:Int = R.string.to,
        var fromLocation:String ="Choose",
        var toLocation : String = "Choose",


    )



    data class PassengerState(
        var title:Int = R.string.passenger,
        var passengers:String = "")


    sealed interface UiAction{

        data object OnClickOneWay :UiAction
        data object OnClickRoundedTrip:UiAction

    }
}