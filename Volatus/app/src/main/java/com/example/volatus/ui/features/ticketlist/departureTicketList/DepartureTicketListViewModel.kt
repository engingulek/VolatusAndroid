package com.example.volatus.ui.features.ticketlist.departureTicketList

import android.util.Log
import androidx.lifecycle.SAVED_STATE_REGISTRY_OWNER_KEY
import androidx.lifecycle.ViewModel

import com.example.volatus.ui.theme.noSelectedDate
import com.example.volatus.ui.theme.selectedDate
import com.example.volatus.utils.FormaterType
import com.example.volatus.utils.formatter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class DayAndPrice(
    val date:LocalDate,
    val price:Int,
    var selectedStateColor:ULong
)

interface  DepartureTicketListViewModelInterface {
   var state : StateFlow<DepartureTicketContract.State>
    fun createDatePrice(getDate:LocalDate)
    fun onAction(onAction:DepartureTicketContract.UiAction)
}

class DepartureTicketListViewModel : ViewModel(), DepartureTicketListViewModelInterface{
    private  val _state = MutableStateFlow(DepartureTicketContract.State())
    override var state: StateFlow<DepartureTicketContract.State> = _state
    private var dateAndPrices : MutableList<DayAndPrice> = mutableListOf()
    private  var oldSelectedIndex:Int? = null




    override fun createDatePrice(getDate:LocalDate) {

        dateAndPrices = mutableListOf()
        val today = LocalDate.now()
        val dateFormatter = DateTimeFormatter.ofPattern("E d MMM")
        for (i in 0..30) {
            val futureDate = today.plusDays(i.toLong())
            val date = futureDate.format(dateFormatter)
            val dateAndPrice = DayAndPrice(
                date= futureDate,
                price = 1500,
                selectedStateColor = if (futureDate == getDate) selectedDate.value else  noSelectedDate.value )
            Log.e("dates","${dateAndPrice}")

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
            oldSelectedIndex = index
            _state.value = updatedState
        }

    }


}