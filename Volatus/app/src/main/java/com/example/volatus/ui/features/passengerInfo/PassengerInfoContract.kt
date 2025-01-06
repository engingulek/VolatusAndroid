package com.example.volatus.ui.features.passengerInfo

import com.example.volatus.ui.features.passenger.Passenger
import java.time.LocalDate

object PassengerInfoContract {
    data class State(
        //val passengerList:List<Passenger> = emptyList(),
        val infoShowList:List<InfoShow> = emptyList(),
        val passengerInfoList:List<PassengerInfo> = emptyList(),
        val passengerInfoError: List<PassengerInfoError> = emptyList()
    )

    sealed interface onAction {
        data class openPassengerInfo(var index:Int):onAction
    }

    sealed interface OnChangeTextFieldAction{
        data class onChangeTRId(var index: Int,var text:String):OnChangeTextFieldAction
        data class onChangeName(var index:Int,var text: String) : OnChangeTextFieldAction
        data class  onChangeSurname(var index:Int, var text: String) :OnChangeTextFieldAction
        data class onSelectedBirthDate(var index: Int,var date:LocalDate): OnChangeTextFieldAction
    }
}