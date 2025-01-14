package com.example.volatus.ui.features.ticketlist.returnTicket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.volatus.R
import com.example.volatus.shared.SharedContract
import com.example.volatus.shared.SharedModel
import com.example.volatus.ui.features.ticketlist.components.DayAndPriceComponent
import com.example.volatus.ui.features.ticketlist.components.TicketComponent
import com.example.volatus.ui.features.ticketlist.components.TicketInfoComponent
import com.example.volatus.utils.conponents.MessageComponent

@Composable
fun ReturnTicketListScreen(
    viewModel:ReturnTicketListViewModelInterface,
    sharedModel:SharedModel,
    navigationPassenger:() -> Unit
) {
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray.copy(0.1f)),
    ) {

        LazyRow( modifier = Modifier
            .fillMaxWidth()
            .background(Color.White).padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            items(state.dayAndPriceList.size) { index ->
                val dayAndPrice = state.dayAndPriceList[index]
                DayAndPriceComponent(dayAndPrice= dayAndPrice) {
                    viewModel.onAction(ReturnTicketListContract.UiAction.onTappedDate(index))
                    sharedModel.onAction(SharedContract.SharedAction.selectedDate(false, date = dayAndPrice.date))
                }
            }
        }
        if(state.listMessage.first){
            MessageComponent(message = state.listMessage.second)
        }else{
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(state.ticketList) { ticket ->
                    TicketComponent(ticket) {
                      sharedModel.onAction(SharedContract.SharedAction.selectedReturnTicket(ticket))
                        navigationPassenger()
                    }
                }
            }
        }


        }
    }
