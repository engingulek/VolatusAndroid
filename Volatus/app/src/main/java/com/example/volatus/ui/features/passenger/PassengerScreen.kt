package com.example.volatus.ui.features.passenger

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PassengerScreen(
    viewModel:PassengerViewModelInterface,
    updatePassenger:(List<Passenger>) -> Unit,
    onBack: () -> Unit
) {
    val uiState by  viewModel.uiState.collectAsState()

    Column(  modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
        ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(uiState.passengerList.size) { index ->
                Column {
                    PassengerComponent(
                        passenger = uiState.passengerList[index],
                        minusAction = {viewModel.onAction(PassengerContract.OnAction.passengerCountMinus(index))},
                        addAction = {viewModel.onAction(PassengerContract.OnAction.passengerCountPlus(index))}

                    )
                    HorizontalDivider(color = Color.Red.copy(alpha = 0.5f))
                }
            }
        }

        Button(
            onClick = {
                updatePassenger(uiState.passengerList)
                onBack()
                      },
            modifier = Modifier.fillMaxWidth()
                .height(50.dp).
                padding(horizontal = 20.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            )
        ) {
            Text(
                stringResource(uiState.confirmButtonTitle),
                style = TextStyle(fontSize = 20.sp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PassengerScreenPreview() {
    PassengerScreen(viewModel = PassengerViewModel(), updatePassenger = {}, onBack = {})

}