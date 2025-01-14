package com.example.volatus.ui.features.passengerInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePicker

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.volatus.R
import com.example.volatus.shared.SharedModel
import com.example.volatus.ui.features.passengerInfo.components.PassengerInfoBirthDateComponent
import com.example.volatus.ui.features.passengerInfo.components.PassengerInfoTextFieldComponent
import com.example.volatus.ui.features.passengerInfo.components.PassengerInfoTypeComponent
import com.example.volatus.ui.features.ticketlist.components.TicketInfoComponent
import com.example.volatus.ui.theme.BackgroundColor
import com.example.volatus.utils.FormaterType
import com.example.volatus.utils.formatter
import java.time.Instant
import java.time.ZoneId


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassengerInfoScreen(
    viewModel:PassengerInfoViewModelInterface,
    sharedModel: SharedModel) {
    val dateState by sharedModel.dateState.collectAsState()
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
    val  state by viewModel.state.collectAsState()

    var isOpen by remember { mutableStateOf(false) }
    val ticketState by sharedModel.ticketState.collectAsState()
    Column(modifier = Modifier.
    fillMaxSize()
        .background(Color.Gray.copy(0.1f))) {
        TicketInfoComponent(
            stringResource(R.string.departureTicketInfo),
            ticket = ticketState.selectedDepartureTicket
        )
        if (dateState.returnState) {
            TicketInfoComponent(
                stringResource(R.string.retrunTicketInfo),
                ticket = ticketState.selectedReturnTicket
            )
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(state.infoShowList.size){ index ->
                val infoShow = state.infoShowList[index]
                PassengerInfoTypeComponent(infoShow){
                    viewModel.onAction(PassengerInfoContract.onAction.openPassengerInfo(index))
                }
               if (infoShow.showState)
                   Column(
                       modifier = Modifier
                           .fillMaxWidth()
                           .background(BackgroundColor),
                       horizontalAlignment = Alignment.CenterHorizontally

                   ) {

                       //TR ID TextField
                       PassengerInfoTextFieldComponent(
                           value = state.passengerInfoList[index].trIdNumber,
                           label = stringResource(R.string.enterId),
                           error = state.passengerInfoError[index].trIdNumberError,
                           keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                       ) { newText->
                           viewModel.onChangeTextFieldAction(
                               PassengerInfoContract.OnChangeTextFieldAction.onChangeTRId(index,newText)
                           )
                       }
                       //Name TextField
                       PassengerInfoTextFieldComponent(
                           value = state.passengerInfoList[index].name,
                           label = stringResource(R.string.enterName),
                           error = state.passengerInfoError[index].nameError,
                           keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                       ) { newText->
                           viewModel.onChangeTextFieldAction(
                               PassengerInfoContract.OnChangeTextFieldAction.onChangeName(index,newText)
                           )
                       }
                       //Surname TextField
                       PassengerInfoTextFieldComponent(
                           value = state.passengerInfoList[index].surname,
                           label = stringResource(R.string.enterSurname),
                           error = state.passengerInfoError[index].surnameError,
                           keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                       ) { newText->
                           viewModel.onChangeTextFieldAction(
                               PassengerInfoContract.OnChangeTextFieldAction.onChangeSurname(index,newText)
                           )
                       }

                       //BirthDate DatePicker
                       PassengerInfoBirthDateComponent(
                           date = state.passengerInfoList[index].birthDate.formatter(FormaterType.TypeFive),
                           errorState = state.passengerInfoError[index].birthDateError
                       ){
                           isOpen = true
                       }

                       if (isOpen) {
                           DatePickerDialog(
                               onDismissRequest = { isOpen = false },
                               confirmButton = {
                                   TextButton(onClick = {
                                    val selectedDate =   Instant.ofEpochMilli(datePickerState.selectedDateMillis ?: 0)
                                           .atZone(ZoneId.systemDefault())
                                           .toLocalDate()
                                       isOpen = false
                                       viewModel.onChangeTextFieldAction(
                                           PassengerInfoContract.OnChangeTextFieldAction.onSelectedBirthDate(index,
                                               selectedDate))
                                   }) { Text(stringResource(R.string.confirmButton))}
                               },
                               dismissButton = {
                                   TextButton(onClick = { isOpen = false })
                                   {Text(stringResource(R.string.cancel)) }
                               }
                           ) {
                               DatePicker(
                                   state = datePickerState,
                               )
                           }
                       }
                   }

                   }
            }
        }

    }

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PassengerInfoScreenPreview() {
    PassengerInfoScreen(viewModel = PassengerInfoViewModel(),sharedModel = SharedModel())
}