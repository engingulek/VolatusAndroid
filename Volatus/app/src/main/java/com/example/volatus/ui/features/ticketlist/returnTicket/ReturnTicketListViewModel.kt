package com.example.volatus.ui.features.ticketlist.returnTicket

import androidx.lifecycle.ViewModel
import com.example.volatus.ui.features.ticketlist.departureTicketList.DayAndPrice
import com.example.volatus.ui.theme.noSelectedDate
import com.example.volatus.ui.theme.selectedDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

interface ReturnTicketListViewModelInterface {
    val  state : StateFlow<ReturnTicketListContract.State>
    fun createDatePrice(departureDate:LocalDate,returnDate:LocalDate)
    fun onAction(onAction:ReturnTicketListContract.UiAction)
}


class ReturnTicketListViewModel : ViewModel(),ReturnTicketListViewModelInterface {
    private val _state = MutableStateFlow(ReturnTicketListContract.State())
    override var state: StateFlow<ReturnTicketListContract.State> = _state

    private  var dateAndPrices : MutableList<DayAndPrice> = mutableListOf()
    private  var oldSelectedIndex:Int? = null


    override fun createDatePrice(departureDate: LocalDate, returnDate: LocalDate) {
        dateAndPrices = mutableListOf()
        val startDate = departureDate
        val dateFormatter = DateTimeFormatter.ofPattern("E d MMM")
        for (i in 0..30){
            val futureDate = startDate.plusDays(i.toLong())
            val dateAndPrice = DayAndPrice(
                date = futureDate,
                price = 1500,
                selectedStateColor = if (futureDate == returnDate) selectedDate.value else noSelectedDate.value
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
            oldSelectedIndex = index
            _state.value = updatedState
        }
    }
}