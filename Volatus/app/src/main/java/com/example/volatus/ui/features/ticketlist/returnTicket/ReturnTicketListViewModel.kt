package com.example.volatus.ui.features.ticketlist.returnTicket

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volatus.R
import com.example.volatus.ui.features.airtportList.Airport
import com.example.volatus.ui.features.ticketlist.departureTicketList.DayAndPrice
import com.example.volatus.ui.theme.noSelectedDate
import com.example.volatus.ui.theme.selectedDate
import com.example.volatus.utils.extensions.FormaterType
import com.example.volatus.utils.extensions.formatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

interface ReturnTicketListViewModelInterface {
    val  state : StateFlow<ReturnTicketListContract.State>
    fun getInfo(fromAirport: Airport?, toAirport: Airport?, departureDate: LocalDate,returnDate: LocalDate)
  //  fun createDatePrice(departureDate:LocalDate,returnDate:LocalDate)
    fun onAction(onAction:ReturnTicketListContract.UiAction)
}

@HiltViewModel
class ReturnTicketListViewModel  @Inject constructor(
    private val service:ReturnTicketListServiceInterface
) : ViewModel(),ReturnTicketListViewModelInterface {
    private val _state = MutableStateFlow(ReturnTicketListContract.State())
    override var state: StateFlow<ReturnTicketListContract.State> = _state

    private var selectedFromAirport :Airport? = null
    private var selectedToAirport:Airport? = null
    override fun getInfo(fromAirport: Airport?,
                         toAirport: Airport?,
                         departureDate: LocalDate,returnDate: LocalDate) {
        selectedFromAirport = fromAirport
        selectedToAirport = toAirport
        createDatePrice(departureDate,returnDate)
        getTicket(returnDate.formatter(FormaterType.TypeFour))
    }


    private fun getTicket(date:String) {


        val fromAirport = selectedFromAirport ?: return
        val toAirport = selectedToAirport ?: return
        Log.e("from","${fromAirport.id}")
        Log.e("to","${toAirport.id}")
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
                    listMessage = if (data.first.isEmpty()) Pair(true, R.string.emptyTicket)
                    else Pair(false, R.string.emptyDefault)

                )
            }
        }

    }

    private  var dateAndPrices : MutableList<DayAndPrice> = mutableListOf()
    private  var oldSelectedIndex:Int? = null


     fun createDatePrice(departureDate: LocalDate, returnDate: LocalDate) {
        dateAndPrices = mutableListOf()

        for (i in 0..30) {
            val futureDate = departureDate.plusDays(i.toLong())
            val dateAndPrice = DayAndPrice(
                date = futureDate,
                price = 1500,
                selectedStateColor = if (futureDate == returnDate) selectedDate.value
                else noSelectedDate.value
            )

            if (futureDate == returnDate) {
                oldSelectedIndex = i
            }

            dateAndPrices.add(dateAndPrice)
        }
        _state.value = _state.value.copy(
            dayAndPriceList =  dateAndPrices
        )
    }

    override fun onAction(onAction: ReturnTicketListContract.UiAction) {
        when(onAction){
            is ReturnTicketListContract.UiAction.onTappedDate -> onTappedDayAction(onAction.index)
        }
    }

    private  fun onTappedDayAction(index:Int){
        oldSelectedIndex?.let {
            val updatedState = _state.value.copy(
                dayAndPriceList =  _state.value.dayAndPriceList.mapIndexed{ idx,dayAndPrice ->
                    if (idx == index){
                        dayAndPrice.copy(selectedStateColor = selectedDate.value)
                    }else if (idx == it){
                        dayAndPrice.copy(selectedStateColor = noSelectedDate.value)
                    }else{
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