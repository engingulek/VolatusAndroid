package com.example.volatus.ui.features.passengerInfo

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.volatus.R
import com.example.volatus.ui.features.passenger.Passenger
import com.example.volatus.utils.isValidTCNumber
import com.example.volatus.utils.subtractYears
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import javax.inject.Inject

interface  PassengerInfoViewModelInterface {
    var state : StateFlow<PassengerInfoContract.State>
    fun getPassengerList(list:List<Passenger>)
    fun onAction(action:PassengerInfoContract.onAction)
    fun onChangeTextFieldAction(action: PassengerInfoContract.OnChangeTextFieldAction)
}

@HiltViewModel
class PassengerInfoViewModel @Inject constructor() : ViewModel(),PassengerInfoViewModelInterface  {
    private val  _state = MutableStateFlow(PassengerInfoContract.State())
    override var state: StateFlow<PassengerInfoContract.State> = _state

    private var infoShowList :MutableList<InfoShow> = mutableListOf()
    private  var passengerInfoList:MutableList<PassengerInfo> = mutableListOf()
    private var passengerInfoErrorList : MutableList<PassengerInfoError> = mutableListOf()

    override fun getPassengerList(list: List<Passenger>) {
        infoShowList = mutableListOf()
        passengerInfoList = mutableListOf()
        val currentDate = LocalDate.now()

        for( passenger in list ){
            for (index in 0..<passenger.count ){
                if (passenger.count != 0){
                    val infoShow = InfoShow(index = index , passengerTitle = passenger.title, showState = false)
                    infoShowList.add(infoShow)

                    val dateRanges = when (passenger.title) {
                        "Adult" -> Pair(currentDate.subtractYears(18), currentDate.subtractYears(80))
                        "Child" -> Pair(currentDate.subtractYears(2), currentDate.subtractYears(11))
                        else -> Pair(currentDate.subtractYears(0), currentDate.subtractYears(11))
                    }

                    val birthDate = when (passenger.title) {
                        "Adult" -> currentDate.subtractYears(18)
                        "Child" -> currentDate.subtractYears(11)
                        else -> currentDate.subtractYears(1)
                    }
                    val passengerInfo = PassengerInfo(
                        id = index,
                        birthDate = birthDate,
                        dateRange = dateRanges
                    )
                    passengerInfoList.add(passengerInfo)
                    passengerInfoErrorList.add(PassengerInfoError())
                }
            }
        }
        _state.value = _state.value.copy(
            infoShowList = infoShowList,
            passengerInfoList = passengerInfoList,
            passengerInfoError = passengerInfoErrorList
        )
    }


    override fun onAction(action: PassengerInfoContract.onAction) {
        when(action){
            is PassengerInfoContract.onAction.openPassengerInfo ->openPassengerInfo(action.index)
        }
    }

    override fun onChangeTextFieldAction(action: PassengerInfoContract.OnChangeTextFieldAction) {
        when(action){
            is PassengerInfoContract.OnChangeTextFieldAction.onChangeTRId -> onChangeTRIdAction(action.index,action.text)
            is PassengerInfoContract.OnChangeTextFieldAction.onChangeName -> onChangNameAction(action.index,action.text)
            is PassengerInfoContract.OnChangeTextFieldAction.onChangeSurname -> onChangSurnameNameAction(action.index,action.text)
            is PassengerInfoContract.OnChangeTextFieldAction.onSelectedBirthDate -> onSelectedBirtDateAction(action.index,action.date)
        }
    }

    private fun openPassengerInfo(index:Int) {
        val updatedState = _state.value.copy(
            infoShowList = _state.value.infoShowList.mapIndexed { idx, infoShow ->
                if (idx == index) {
                    val newShowState = !infoShow.showState
                    infoShow.copy(showState = newShowState)
                } else {
                    infoShow
                }
            }
        )
        _state.value = updatedState
    }

    private fun onChangeTRIdAction(index: Int,text:String){
        val control = text.isValidTCNumber()
        _state.value = _state.value.copy(
            passengerInfoList = _state.value.passengerInfoList.mapIndexed { idx, passengerInfo ->
                if (idx == index && text.length <= 11) {
                    passengerInfo.copy(trIdNumber = text)
                } else {
                    passengerInfo
                }
            },
            passengerInfoError = _state.value.passengerInfoError.mapIndexed { idx, passengerInfoError ->
                if (idx == index) {
                    passengerInfoError.copy(
                        trIdNumberError =
                        if (control) Pair(R.string.emptyDefault, false)
                        else Pair(R.string.checkId, true)
                    )
                } else {
                    passengerInfoError
                }
            }
        )

    }

    private fun onChangNameAction(index:Int,text: String){

        val errorState =
            if(text.isEmpty()) Pair(R.string.blankError,true)
            else if (text.length < 2)  Pair(R.string.blankError,true)
            else  Pair(R.string.emptyDefault,false)

        _state.value = _state.value.copy(
            passengerInfoList = _state.value.passengerInfoList.mapIndexed { idx, passengerInfo ->
                if (idx == index) {
                    passengerInfo.copy(name = text)
                } else {
                    passengerInfo
                }
            },
            passengerInfoError = _state.value.passengerInfoError.mapIndexed { idx, passengerInfoError ->
                if (idx == index) {
                    passengerInfoError.copy(
                        nameError = errorState
                    )
                } else {
                    passengerInfoError
                }
            }
        )
    }

    private fun onChangSurnameNameAction(index:Int,text: String){

        val errorState =
            if(text.isEmpty()) Pair(R.string.blankError,true)
            else if (text.length < 2)  Pair(R.string.shortError,true)
            else  Pair(R.string.emptyDefault,false)

        _state.value = _state.value.copy(
            passengerInfoList = _state.value.passengerInfoList.mapIndexed { idx, passengerInfo ->
                if (idx == index) {
                    passengerInfo.copy(surname = text)
                } else {
                    passengerInfo
                }
            },
            passengerInfoError = _state.value.passengerInfoError.mapIndexed { idx, passengerInfoError ->
                if (idx == index) {
                    passengerInfoError.copy(
                        surnameError = errorState
                    )
                } else {
                    passengerInfoError
                }
            }
        )
    }

    private fun onSelectedBirtDateAction(index: Int, date:LocalDate){
        val dateRange = passengerInfoList[index].dateRange
        val errorState =
        if( date.isEqual(dateRange.second) || (date.isAfter(dateRange.second) && date.isBefore(dateRange.first)))
            Pair(R.string.emptyDefault,false)
        else Pair(R.string.dateError,true)
        

        _state.value = _state.value.copy(
            passengerInfoList = _state.value.passengerInfoList.mapIndexed { idx, passengerInfo ->
                if (idx == index) {
                    passengerInfo.copy(birthDate = date)
                } else {
                    passengerInfo
                }
            },
            passengerInfoError = _state.value.passengerInfoError.mapIndexed { idx, passengerInfoError ->
                if (idx == index) {
                    passengerInfoError.copy(
                        birthDateError = errorState
                    )
                } else {
                    passengerInfoError
                }
            }
        )
    }
}