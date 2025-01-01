package com.example.volatus.ui.features.date

import android.app.DatePickerDialog
import android.os.Build
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
    val calendarData = viewModel.result

     Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
         LazyColumn {
             items(calendarData.size) { index ->
                 val monthAndYear = calendarData[index].keys.first()
                 val days = calendarData[index].values.flatten()
                 Column(modifier = Modifier.padding(vertical = 10.dp)) {
                     Text(monthAndYear, style = TextStyle(
                         fontSize = 20.sp, fontWeight =
                         FontWeight.SemiBold)
                     )

                     LazyVerticalGrid(
                         columns = GridCells.Fixed(7),
                         modifier = Modifier.height(50.dp)

                     ) {
                         items(viewModel.weeks) { day ->
                             Card(modifier = Modifier.padding(4.dp),
                                 colors = CardDefaults.cardColors(
                                 containerColor = Color.Transparent
                             )) {
                                 Text(
                                     text = day,
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
                     LazyVerticalGrid(
                         columns = GridCells.Fixed(7), // 7 sütun (Pazartesi, Salı, ...)
                         modifier = Modifier.fillMaxWidth()
                             .height(230.dp)
                     ) {
                         items(days){ day ->

                             if (day.second != null){
                                 Card(
                                     modifier = Modifier.padding(4.dp),
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

    /*LazyColumn(modifier = Modifier.fillMaxWidth().padding(16.dp)) {

        items(calendarData) { monthData -> // Veriyi burada ayın verisi ile geçiyoruz
            Column(modifier = Modifier.padding(bottom = 24.dp)) {

                // Her bir ay için başlık (dinamik)
              //  Text(text = monthData.monthName, style = MaterialTheme.typography.labelLarge)

                // Haftanın günleri
                LazyVerticalGrid(
                    columns = GridCells.Fixed(7), // 7 sütun (Pazartesi, Salı, ...)
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(listOf("Pzt", "Sal", "Çar", "Per", "Cum", "Cts", "Paz")) { day ->
                        Card(modifier = Modifier.padding(4.dp)) {
                            Text(
                                text = day,
                                modifier = Modifier.padding(8.dp),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }

                // Ayın tarihleri
                /*LazyVerticalGrid(
                    columns = GridCells.Fixed(7), // 7 sütun (Pazartesi, Salı, ...)
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Burada monthData.dates veya uygun tarih verisini kullanarak grid'i oluşturuyoruz.
                    items(monthData.dates) { date ->
                        Card(modifier = Modifier.padding(4.dp)) {
                            Text(
                                text = date.dayOfMonth.toString(), // Gün numarasını göstermek
                                modifier = Modifier.padding(8.dp),
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }*/
            }
        }
    }*/
}
