package com.example.volatus.ui.features.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.volatus.R
import com.example.volatus.ui.features.airtportList.Airport
import com.example.volatus.ui.features.passenger.Passenger
import kotlinx.coroutines.IO_PARALLELISM_PROPERTY_NAME
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

interface HomeViewModelInterface{
    var uiState : StateFlow<HomeContract.UiState>
    var dateState : StateFlow<HomeContract.DateState>
    var tripState : StateFlow<HomeContract.TripTypeState>
    var locationState : StateFlow<HomeContract.LocationState>
    var passengerState : StateFlow<HomeContract.PassengerState>


    fun onAction(uiAction: HomeContract.UiAction)
    fun publicOnAction(onAction:HomeContract.PublicUiAction)
}


class HomeViewModel : ViewModel(), HomeViewModelInterface{

    private val _uiState = MutableStateFlow(HomeContract.UiState())
    override  var uiState : StateFlow<HomeContract.UiState> = _uiState

    private val _dateState  =  MutableStateFlow(HomeContract.DateState())
    override var dateState : StateFlow<HomeContract.DateState> = _dateState

    private val _tripState = MutableStateFlow(HomeContract.TripTypeState())
    override var tripState: StateFlow<HomeContract.TripTypeState> = _tripState

    private val _locationState = MutableStateFlow(HomeContract.LocationState())
    override var locationState: StateFlow<HomeContract.LocationState> = _locationState

    private val _passengerState = MutableStateFlow(HomeContract.PassengerState())
    override var passengerState: StateFlow<HomeContract.PassengerState> = _passengerState


    private  var selectedFromAirport:Airport? = null
    private  var selectedToAirport:Airport? = null

    private var selectedDepartureDate:LocalDate = LocalDate.now()
    private  var selectedReturnDate:LocalDate? = null
    private  val formatter = DateTimeFormatter.ofPattern("MMMM dd,yyyy")

    init {


        _dateState.value = _dateState.value.copy(
            departureDate = formatter.format(LocalDate.now()),
            returnDate =  formatter.format(LocalDate.now())
        )

        val passengerList:List<Passenger> = listOf(
            Passenger(title = "Adult",
                ageSpaceTitle = R.string.adult_range_title,
                1,true),
            Passenger(title = "Child",
                ageSpaceTitle = R.string.child_range_title,
                0,true),
            Passenger(title = "Baby",
                ageSpaceTitle = R.string.baby_range_title,
                0,true),
        )

        val list = passengerList.map { if (it.count != 0) "${it.count} ${it.title}" else "" }
        val text = list.filter { it.isNotEmpty() }.joinToString(",")

        _passengerState.value = _passengerState.value.copy(
            passengerList = passengerList,
            passengerText = text
        )


    }



    override fun onAction(uiAction: HomeContract.UiAction) {
        when(uiAction){

            HomeContract.UiAction.OnClickOneWay ->  clickedOneWay()
            HomeContract.UiAction.OnClickRoundedTrip ->clickedRoundedWay()
            HomeContract.UiAction.OnClickSwapIcon -> onClickSwapIconAction()
            //TODO: These will be moved to publicOnAction


        }
    }

    override fun publicOnAction(onAction: HomeContract.PublicUiAction) {
        when(onAction){
            is HomeContract.PublicUiAction.updatePassengerList -> updatePassengerListAction(onAction.passengerList)
            is HomeContract.PublicUiAction.selectedAirport ->  selectedAirportAction(onAction.type,onAction.airport)
            is HomeContract.PublicUiAction.selectedDate -> selectedDateAction(onAction.type,onAction.date)
        }

    }

    private fun clickedOneWay(){
        _dateState.value = _dateState.value.copy(
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

        _dateState.value = _dateState.value.copy(
            returnVisible = false,
           returnDate = formatter.format(selectedDepartureDate)
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

    private fun selectedAirportAction(type:Boolean?,airport: Airport){
        val airportText = "${airport.code} - ${airport.name}"
        type?.let {
            if (it){
                selectedFromAirport = airport

                _locationState.value = _locationState.value.copy(
                    fromLocation =  airportText
                )
            }else{
                selectedToAirport = airport
                _locationState.value = _locationState.value.copy(
                    toLocation =  airportText
                )
            }
        }
    }

    private fun selectedDateAction(type:Boolean?,date:LocalDate) {

        val dateText = formatter.format(date)

        type?.let {
            if (it){
               selectedDepartureDate = date
                _dateState.value = _dateState.value.copy(
                    departureDate =  dateText
                )
            }else{
                selectedReturnDate = date
                _dateState.value = _dateState.value.copy(
                    returnDate = dateText
                )
            }
        }
    }

    private fun onClickSwapIconAction(){
        val tempAirport = selectedFromAirport
        selectedFromAirport = selectedToAirport
        selectedToAirport = tempAirport

        _locationState.value = _locationState.value.copy(
            fromLocation =  _locationState.value.toLocation,
            toLocation = _locationState.value.fromLocation

        )
    }

    private fun updatePassengerListAction(passengerList:List<Passenger>){
        val list = passengerList.map { if (it.count != 0) "${it.count} ${it.title}" else "" }
        val text = list.filter { it.isNotEmpty() }.joinToString(",")

        _passengerState.value = _passengerState.value.copy(
            passengerList = passengerList,
            passengerText = text
        )
    }






}