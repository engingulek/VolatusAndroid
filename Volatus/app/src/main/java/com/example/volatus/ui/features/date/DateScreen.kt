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
import com.example.volatus.shared.SharedModel
import com.example.volatus.utils.getDateCardColor

import com.example.volatus.utils.getDateColor

import java.time.LocalDate


@Composable
fun DateScreen(
    viewModel: DateViewModel,
    sharedModel: SharedModel,
    selectDateAction:(LocalDate) -> Unit,
    onBack: () -> Unit

    ) {

    val mount by viewModel.mountCalender.collectAsState()
    val dayCalendar by viewModel.daysCalender.collectAsState()
    val navTitle by viewModel.navTitle.collectAsState()
     Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
         Text(modifier = Modifier.fillMaxWidth(),
             text = navTitle, style = TextStyle(
                 fontSize = 20.sp,
                 fontWeight = FontWeight.SemiBold,
                 textAlign = TextAlign.Center
             )
         )
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
                                         ) {
                                             viewModel.selectedData(mountIndex,dayIndex)
                                            val selectedDate = viewModel.getSelectedDate(mountIndex,day.second)
                                             selectDateAction(selectedDate)
                                             onBack()

                                           },

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
