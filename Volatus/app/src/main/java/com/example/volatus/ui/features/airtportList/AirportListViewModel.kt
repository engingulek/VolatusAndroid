package com.example.volatus.ui.features.airtportList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volatus.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface  AirportListViewModelInterface {
    var uiState : StateFlow<AirportContract.UiState>

    fun onAction(action:AirportContract.UiAction)

}

@HiltViewModel
class AirportListViewModel @Inject constructor(private val service: AirportServiceInterface)

    :ViewModel(),AirportListViewModelInterface {
    private val _uiState = MutableStateFlow(AirportContract.UiState())
    override var uiState: StateFlow<AirportContract.UiState> = _uiState

    private var tempList : List<Airport> = emptyList()

    init {
        // fetch airport
        fetchAirportList()
    }


    fun test(){}

    private fun fetchAirportList() {
        viewModelScope.launch {
            service.fetchAllAirportList()
            val data = service.getAllAirportList()
            if (data.second) {
                _uiState.value = _uiState.value.copy(
                    airportList =  emptyList(),
                    listMessage = Pair(true,R.string.errorMessage)
                )

                tempList = emptyList()

            }else{
                _uiState.value = _uiState.value.copy(
                    airportList = data.first,
                    listMessage = if (data.first.isEmpty() ) Pair(true,R.string.emptyAirport)
                    else Pair(false,R.string.emptyDefault)
                )
                tempList = data.first

            }
        }
    }


    override fun onAction(action: AirportContract.UiAction) {
        when(action){
            is AirportContract.UiAction.onSearchAction -> onSearchAction(action.text)
        }
    }

    private fun onSearchAction(text:String) {

        if(text.isEmpty()){
            _uiState.value = _uiState.value.copy(
                airportList = tempList.toMutableList(),
                listMessage = Pair(false, R.string.emptyDefault)
            )
        }else{
           val filterList = tempList.filter { it.airname.lowercase().contains(text.lowercase()) }
            _uiState.value = _uiState.value.copy(
                airportList = filterList,
                listMessage =
                if (filterList.isEmpty()) Pair(true, R.string.listAirportEmpty)
                else Pair(false, R.string.emptyDefault)
            )
        }
    }
}