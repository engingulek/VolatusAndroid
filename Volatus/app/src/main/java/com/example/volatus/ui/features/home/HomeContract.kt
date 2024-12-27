package com.example.volatus.ui.features.home

import androidx.annotation.Nullable
import com.example.volatus.R
import com.example.volatus.ui.theme.ImageType

object HomeContract {
    data class UiState(
        var backImage:ImageType= ImageType(
            R.drawable.background,
            R.string.worldMapContent),

        var swapIcon:ImageType = ImageType(
            R.drawable.swap_icon,
            R.string.swapIconsContent),

        var searchButtonTitle:Int =  R.string.search,

        var oneWayTripeType: TripeType = TripeType(
            title = R.string.oneway,
            contentColor = R.color.white,
            containerColor = R.color.red
        ),
        var roundedTripeType: TripeType = TripeType(
            title = R.string.roundTrip,
            contentColor = R.color.red,
            containerColor = R.color.white
        ),
        var fromLocationComponent:LocationComponentData  =LocationComponentData(
            title = R.string.from
        ),
        var toLocationComponent:LocationComponentData = LocationComponentData(
            title = R.string.to
        ),

        var departureTimeComponent:TimeComponentData = TimeComponentData(
            title = R.string.departure,
            visible = false
        ),
        var returnTimeComponentData: TimeComponentData = TimeComponentData(
            title = R.string.returnTitle,
            visible = true
        ),
        var passengerComponentData: PassengerComponentData = PassengerComponentData(
            title = R.string.passenger,
            passengers = "1 Adult"
        ),



    )

    data class  TripeType(
        var title:Int = R.string.emptyDefault,
        var contentColor:Int,
        var containerColor:Int
        )

    data class LocationComponentData(
        var title:Int = R.string.emptyDefault
    )

    data class TimeComponentData(
        var title:Int = R.string.emptyDefault,
        var visible:Boolean = false
    )

    data class PassengerComponentData(
        var title:Int = R.string.emptyDefault,
        var passengers:String)
}