package com.example.volatus.ui.features.ticketlist.departureTicketList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.volatus.R
import com.example.volatus.ui.features.airtportList.Airport

import com.example.volatus.ui.theme.noSelectedDate
import com.example.volatus.ui.theme.selectedDate
import com.example.volatus.utils.FormaterType
import com.example.volatus.utils.formatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

data class DayAndPrice(
    val date:LocalDate,
    val price:Int,
    var selectedStateColor:ULong
)

interface  DepartureTicketListViewModelInterface {
   var state : StateFlow<DepartureTicketContract.State>
    fun getInfo(fromAirport:Airport?,toAirport: Airport?,getDate:LocalDate)
    fun onAction(onAction:DepartureTicketContract.UiAction)
}

@HiltViewModel
class DepartureTicketListViewModel
    @Inject  constructor(private val service:DepartureTicketListServiceInterface)

    : ViewModel(), DepartureTicketListViewModelInterface{
    private  val _state = MutableStateFlow(DepartureTicketContract.State())
    override var state: StateFlow<DepartureTicketContract.State> = _state
    private var dateAndPrices : MutableList<DayAndPrice> = mutableListOf()
    private  var oldSelectedIndex:Int? = null
    private var selectedFromAirport :Airport? = null
    private var selectedToAirport:Airport? = null

    override fun getInfo(fromAirport: Airport?, toAirport: Airport?, getDate: LocalDate) {
        createDatePrice(getDate)
        selectedFromAirport = fromAirport
        selectedToAirport = toAirport
        getTicket(getDate.formatter(FormaterType.TypeFour))
    }

    private fun getTicket(date:String) {
      /*  Log.e("date","${date}")
        Log.e("selectedFromAirport","${selectedFromAirport?.id}")
        Log.e("selectedToAirport","${selectedToAirport?.id}")*/

        val fromAirport = selectedFromAirport ?: return
        val toAirport = selectedToAirport ?: return
        viewModelScope.launch {
            service.fetchTicketList(fromAirport.id,toAirport.id,date)
            val data = service.getTicketList()
            if (data.second){
               _state.value = _state.value.copy(
                   ticketList = emptyList(),
                   listMessage = Pair(true, R.string.errorMessage)
               )
            }else{
               _state.value = _state.value.copy(
                   ticketList = data.first,
                   listMessage = if (data.first.isEmpty()) Pair(true,R.string.emptyTicket)
                   else Pair(false,R.string.emptyDefault)

               )
            }
        }

    }

    private fun createDatePrice(getDate:LocalDate) {

        dateAndPrices = mutableListOf()
        val today = LocalDate.now()
        for (i in 0..30) {
            val futureDate = today.plusDays(i.toLong())
            val dateAndPrice = DayAndPrice(
                date= futureDate,
                price = 1500,
                selectedStateColor = if (futureDate == getDate) selectedDate.value else  noSelectedDate.value )

            if (futureDate == getDate) {
                oldSelectedIndex = i
            }
            dateAndPrices.add(dateAndPrice)

        }
        _state.value = _state.value.copy(
           dayAndPriceList = dateAndPrices
        )
    }

    override fun onAction(onAction: DepartureTicketContract.UiAction) {
        when(onAction) {
            is DepartureTicketContract.UiAction.onTappedDate -> onTappedDayAction(onAction.index)
        }
    }


    private fun onTappedDayAction(index:Int) {
        oldSelectedIndex?.let {
            val updatedState = _state.value.copy(
                dayAndPriceList = _state.value.dayAndPriceList.mapIndexed { idx, dayAndPrice ->
                    if (idx == index) {
                        dayAndPrice.copy(selectedStateColor = selectedDate.value)

                    }else if (idx == it){
                        dayAndPrice.copy(selectedStateColor = noSelectedDate.value)
                    }
                    else {
                        dayAndPrice
                    }
                }
            )
            val date = dateAndPrices[index].date.formatter(FormaterType.TypeFour)
            getTicket(date)
            oldSelectedIndex = index
            _state.value = updatedState
        }
    }
}