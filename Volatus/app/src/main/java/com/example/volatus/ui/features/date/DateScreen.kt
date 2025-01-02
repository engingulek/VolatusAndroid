package com.example.volatus.ui.features.date

import android.app.DatePickerDialog
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


import java.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridItemSpan

import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.volatus.utils.getDateCardColor

import com.example.volatus.utils.getDateColor

import java.time.LocalDate
import java.time.YearMonth

import java.util.*


@Composable
fun DateScreen(viewModel: DateViewModel = DateViewModel()) {

    val mount by viewModel.mountCalender.collectAsState()
    val dayCalendar by viewModel.daysCalender.collectAsState()
     Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
         LazyColumn {
             items(mount.keys.size) { mountIndex ->
                 val monthAndYear = mount[mountIndex]

                 Column(modifier = Modifier.padding(vertical = 10.dp)) {
                     Text(monthAndYear ?: "", style = TextStyle(
                         fontSize = 20.sp, fontWeight =
                         FontWeight.SemiBold)
                     )

                     LazyVerticalGrid(
                         columns = GridCells.Fixed(7),
                         modifier = Modifier.height(50.dp)

                     ) {
                         items(viewModel.weeks) { week ->
                             Card(modifier = Modifier.padding(4.dp),
                                 colors = CardDefaults.cardColors(
                                 containerColor = Color.Transparent
                             )) {
                                 Text(
                                     text = week,
                                     modifier = Modifier.padding(5.dp),
                                       style = TextStyle(
                                         color = Color.Gray,

                                         textAlign = TextAlign.Center,
                                           fontSize = 18.sp,
                                           fontWeight = FontWeight.SemiBold


                                       )
                                 )
                             }
                         }

                     }
                     val days = dayCalendar[mountIndex] ?: emptyList()
                     LazyVerticalGrid(
                         columns = GridCells.Fixed(7),
                         modifier = Modifier.fillMaxWidth()
                             .height(230.dp)


                     ) {
                         items(days.size){ dayIndex ->
                             val day = days[dayIndex]
                             if (day.second != null){
                                 Card(
                                     modifier = Modifier.padding(4.dp)
                                         .clickable(
                                             enabled = day.first != DateValueType.DISABLE
                                         ) {  viewModel.selectedData(mountIndex,dayIndex)},

                                     colors = CardDefaults.cardColors(
                                         containerColor = day.first.getDateCardColor()
                                     )
                                 ) {

                                     Text(
                                         text = "${day.second}",
                                         modifier = Modifier.padding(8.dp).fillMaxSize(),
                                         style = TextStyle(
                                             color = day.first.getDateColor(),
                                             fontSize = 18.sp,
                                             textAlign = TextAlign.Center)

                                     )
                                 }
                             }


                         }
                     }
                 }

             }
         }

     }

}
