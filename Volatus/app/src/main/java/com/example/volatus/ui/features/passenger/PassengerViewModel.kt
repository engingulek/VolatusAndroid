package com.example.volatus.ui.features.passenger

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

interface PassengerViewModelInterface {
    var uiState:StateFlow<PassengerContract.UiState>
    fun getPassengerList(passengerList:List<Passenger>)
    fun onAction(onAction:PassengerContract.OnAction)
}

@HiltViewModel
class PassengerViewModel @Inject constructor() : ViewModel(),PassengerViewModelInterface {
    private  val _uiState = MutableStateFlow(PassengerContract.UiState())
    override var uiState: StateFlow<PassengerContract.UiState> = _uiState

    override fun getPassengerList(passengerList: List<Passenger>) {
        _uiState.value = _uiState.value.copy(
            passengerList = passengerList
        )
    }

    override fun onAction(onAction: PassengerContract.OnAction) {
        when(onAction){
            is PassengerContract.OnAction.passengerCountMinus -> minusPassengerAction(onAction.index)
            is PassengerContract.OnAction.passengerCountPlus -> addPassengerAction(onAction.index)
        }
    }

    private fun minusPassengerAction(index:Int){
        val updatedList = _uiState.value.passengerList.toMutableList()

        val count = updatedList[index].count - 1
        val title = updatedList[index].title
        updatedList[index] = updatedList[index].copy(
            count = if (title == "Adult" && count == 0) 1 else count,
            minusButtonStatus = count == 0

        )
        _uiState.value = _uiState.value.copy(
            passengerList = updatedList
        )
    }

    private fun addPassengerAction(index: Int) {

        val updatedList = _uiState.value.passengerList.toMutableList()


        updatedList[index] = updatedList[index].copy(
            count = updatedList[index].count + 1,
            minusButtonStatus = false
        )


        _uiState.value = _uiState.value.copy(
            passengerList = updatedList
        )
    }


}