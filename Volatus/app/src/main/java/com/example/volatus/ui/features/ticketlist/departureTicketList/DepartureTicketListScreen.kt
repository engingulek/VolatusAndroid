package com.example.volatus.ui.features.ticketlist.departureTicketList

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.volatus.shared.SharedContract
import com.example.volatus.shared.SharedModel
import com.example.volatus.ui.features.ticketlist.components.DayAndPriceComponent
import com.example.volatus.ui.features.ticketlist.components.TicketComponent
import com.example.volatus.ui.theme.errorColor
import com.example.volatus.utils.conponents.MessageComponent

@Composable
fun DepartureTicketListScreen(
    viewModel:DepartureTicketListViewModelInterface,
    sharedModel: SharedModel,
    navigationPassenger:() -> Unit,
    navigationReturnTicketList:() -> Unit
) {
    val state by viewModel.state.collectAsState()
    val sharedState by sharedModel.dateState.collectAsState()
    Column(modifier = Modifier.
        fillMaxSize()
        .background(Color.Gray.copy(0.1f))) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White).padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)

        ) {
            items(state.dayAndPriceList.size) { index ->
                val dayAndPrice = state.dayAndPriceList[index]
                DayAndPriceComponent(dayAndPrice = dayAndPrice) {
                    viewModel.onAction(DepartureTicketContract.UiAction.onTappedDate(index))
                    sharedModel.onAction(SharedContract.SharedAction.selectedDate(true, date = dayAndPrice.date))
                }
            }
        }
        if (state.listMessage.first){
            MessageComponent(message = state.listMessage.second)
        }else{
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(state.ticketList) { ticket ->
                    TicketComponent(ticket) {
                        sharedModel.onAction(SharedContract.SharedAction.selectedDepartureTicket(ticket))
                        if (sharedState.returnState)
                            navigationReturnTicketList()
                        else
                            navigationPassenger()
                    }
                }
        }

        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DepartureTicketListScreenPreview() {
    DepartureTicketListScreen(
        viewModel = hiltViewModel<DepartureTicketListViewModel>(),
        sharedModel = SharedModel(),
        navigationPassenger = {},
        navigationReturnTicketList = {} )
}