package com.example.volatus.ui.features.ticketlist.departureTicketList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.volatus.shared.SharedContract
import com.example.volatus.shared.SharedModel
import com.example.volatus.ui.features.ticketlist.components.DayAndPriceComponent
import com.example.volatus.ui.theme.selectedDate
import com.example.volatus.utils.FormaterType
import com.example.volatus.utils.formatter
import androidx.compose.ui.text.style.TextAlign.Companion as TextAlign1

@Composable
fun DepartureTicketListScreen(
    viewModel:DepartureTicketListViewModelInterface,
    sharedModel: SharedModel
) {
    val state by viewModel.state.collectAsState()
    Column(modifier = Modifier.
        fillMaxSize()
        .background(Color.Gray.copy(0.1f))) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)

                .padding(10.dp)
            ,

            horizontalArrangement = Arrangement.spacedBy(15.dp)

        ) {
            items(state.dayAndPriceList.size) { index ->
                val dayAndPrice = state.dayAndPriceList[index]
                Column(
                    Modifier.clickable {
                        viewModel.onAction(DepartureTicketContract.UiAction.onTappedDate(index))
                        sharedModel.onAction(SharedContract.SharedAction.selectedDate(true, date = dayAndPrice.date))
                    },
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Text(
                        text = dayAndPrice.date.formatter(FormaterType.TypeFour),
                        style = TextStyle(fontSize = 20.sp, color = Color.Gray),
                        textAlign = TextAlign.Center,
                        color = Color( dayAndPrice.selectedStateColor)
                    )
                    Text(
                        text = "TRY ${dayAndPrice.price}",
                        style = TextStyle(fontSize = 15.sp,color = Color.Gray),
                        textAlign = TextAlign.Center,
                        color = Color( dayAndPrice.selectedStateColor)
                    )
                    Divider(
                        modifier = Modifier
                            .width(65.dp)
                            .height(2.dp),
                        color = Color( dayAndPrice.selectedStateColor)
                    )
                }
            }
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DepartureTicketListScreenPreview() {
    DepartureTicketListScreen(viewModel = DepartureTicketListViewModel(), sharedModel = SharedModel() )
}