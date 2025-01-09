package com.example.volatus.ui.features.date

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.volatus.ui.features.date.components.DayComponent
import com.example.volatus.ui.features.date.components.WeekComponent
import com.example.volatus.utils.getDateCardColor

import com.example.volatus.utils.getDateColor

import java.time.LocalDate


@Composable
fun DateScreen(
    viewModel: DateViewModelInterface,
    selectDateAction: (LocalDate) -> Unit,
    onBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        LazyColumn {
            items(state.mountCalender.keys.size) { mountIndex ->
                val monthAndYear = state.mountCalender[mountIndex]
                Column(modifier = Modifier.padding(vertical = 10.dp)) {
                    Text(
                        monthAndYear ?: "", style = TextStyle(
                            fontSize = 20.sp, fontWeight =
                            FontWeight.SemiBold
                        )
                    )

                    //Week List
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(7),
                        modifier = Modifier.height(50.dp)

                    ) {
                        items(state.weeks) { week ->
                        WeekComponent(week)
                        }
                    }

                    //Day List
                    val days = state.daysCalender[mountIndex] ?: emptyList()
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(7),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp)

                    ) {
                        items(days.size) { dayIndex ->
                            val day = days[dayIndex]
                            if (day.second != null) {
                                DayComponent(day) {
                                    viewModel.selectedData(mountIndex, dayIndex)
                                    val selectedDate =
                                        viewModel.getSelectedDate(mountIndex, day.second)
                                    selectDateAction(selectedDate)
                                    onBack()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
