package com.example.volatus.sharedModel

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.volatus.shared.SharedContract
import com.example.volatus.shared.SharedModel
import com.example.volatus.ui.features.airtportList.Airport
import com.example.volatus.utils.FormaterType
import com.example.volatus.utils.formatter
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class SharedModelTests {
    private lateinit var model:SharedModel
    @Before
    fun setup(){
        model = SharedModel()
    }


    @Test
    fun `When open HomeScreen , return dateState on SharedContract `(){
        val dateState = model.dateState.value

        val nowDate = LocalDate.now()

        assertEquals(
            "departure date is not correct",
            nowDate,
            dateState.departureDate)

        val expectedDepartureDateText = nowDate.formatter(FormaterType.TypeThree)
        assertEquals(
            "departure date text is not correct",
            expectedDepartureDateText,
            dateState.departureDateText)


        assertFalse(
            "returnState is not correct",
            dateState.returnState)

    }

    @Test
    fun `When onClick roundedTripe, return return dateState on SharedContract`(){
        val dateState = model.dateState.value

        val nowDate = LocalDate.now()
        model.onAction(SharedContract.SharedAction.updateReturnState(true))

        val expectedReturnDateText = nowDate.formatter(FormaterType.TypeThree)
        assertEquals(
            "returnDateText is not correct",
            expectedReturnDateText,
            dateState.returnDateText)

    }


    @Test
    fun `When open HomeScreen , return AirportUiState on SharedContract `(){
        val airportState = model.airportUiState.value

        assertEquals(
            "fromAirportTextString is not correct",
            "Choose",
            airportState.fromAirportTextString
            )

        assertEquals(
            "toAirportText is not correct",
            "Choose",
            airportState.toAirportText
        )

        assertFalse("airportState is not correct",airportState.airportState)
    }

    @Test fun `When selected from and to airport, return  fromAirportTextString and toAirportText on AirportUiState `(){


        model.onAction(SharedContract.SharedAction.selectedAirport(
            true,
            Airport( 1,
                "Los Angeles International Airport",
                "LAX",  "Los Angeles",
                "United States"),))


        model.onAction(SharedContract.SharedAction.selectedAirport(
            false,
            Airport(2,
                "London Heathrow Airport",
                "LHR",
                "London", "United Kingdom"),))


        val expectedFromAirportTextString = "LAX - Los Angeles International Airport"

        val expectedToAirportText = "LHR - London Heathrow Airport"
        val airportState = model.airportUiState.value

        assertEquals(
            "fromAirportTextString is not correct",
            expectedFromAirportTextString,
            airportState.fromAirportTextString
            )

        assertEquals(
            "toAirportText is not correct",
            expectedToAirportText,
            airportState.toAirportText
        )
    }

    @Test fun `When selected same airport  return airportState `(){
        model.onAction(SharedContract.SharedAction.selectedAirport(
            true,
            Airport( 1,
                "Los Angeles International Airport",
                "LAX",  "Los Angeles",
                "United States"),))

        model.onAction(SharedContract.SharedAction.selectedAirport(
            false,
            Airport( 1,
                "Los Angeles International Airport",
                "LAX",  "Los Angeles",
                "United States"),))
        val airportState = model.airportUiState.value

        assertFalse(
            "airportState is not correct",
            airportState.airportState)
    }


}