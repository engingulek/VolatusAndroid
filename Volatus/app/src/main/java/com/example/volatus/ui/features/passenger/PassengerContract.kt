package com.example.volatus.ui.features.passenger

import com.example.volatus.R

object PassengerContract {
    data class UiState(
        var confirmButtonTitle:Int = R.string.confirmButton,
        var passengerList:List<Passenger> = emptyList()
    )


    sealed interface OnAction {
        data class passengerCountMinus(val index:Int) :OnAction
        data class  passengerCountPlus(val index:Int) :OnAction
    }

}