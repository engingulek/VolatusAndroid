package com.example.volatus.ui.features.home

import androidx.lifecycle.ViewModel
import com.example.volatus.R
import com.example.volatus.shared.SharedContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface HomeViewModelInterface{
    var uiState : StateFlow<HomeContract.UiState>

    var tripState : StateFlow<HomeContract.TripTypeState>




    fun onAction(uiAction: HomeContract.UiAction)

}



class HomeViewModel : ViewModel(), HomeViewModelInterface{

    private val _uiState = MutableStateFlow(HomeContract.UiState())
    override  var uiState : StateFlow<HomeContract.UiState> = _uiState


    private val _tripState = MutableStateFlow(HomeContract.TripTypeState())
    override var tripState: StateFlow<HomeContract.TripTypeState> = _tripState

    override fun onAction(uiAction: HomeContract.UiAction) {
        when(uiAction){

            HomeContract.UiAction.OnClickOneWay ->  clickedOneWay()
            HomeContract.UiAction.OnClickRoundedTrip ->clickedRoundedWay()

            //TODO: These will be moved to publicOnAction


        }
    }



    private fun clickedOneWay(){
        _uiState.value = _uiState.value.copy(
            returnVisible = true
        )


        _tripState.value = _tripState.value.copy(

            oneWayTripeType = HomeContract.TripeTypeData(
                containerColor = R.color.red,
                contentColor = R.color.white
            ),
            roundedTripeType = HomeContract.TripeTypeData(
                containerColor = R.color.white,
                contentColor = R.color.red
            )
        )
    }

    private fun clickedRoundedWay(){

        _uiState.value = _uiState.value.copy(
            returnVisible = false,

        )


        _tripState.value = _tripState.value.copy(

            oneWayTripeType = HomeContract.TripeTypeData(
                containerColor = R.color.white,
                contentColor = R.color.red
            ),
            roundedTripeType = HomeContract.TripeTypeData(
                containerColor = R.color.red,
                contentColor = R.color.white
            )
        )
    }
}