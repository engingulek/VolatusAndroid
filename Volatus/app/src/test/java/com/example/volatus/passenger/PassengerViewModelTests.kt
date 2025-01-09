package com.example.volatus.passenger

import com.example.volatus.shared.SharedContract
import com.example.volatus.shared.SharedModel
import com.example.volatus.ui.features.passenger.PassengerContract
import com.example.volatus.ui.features.passenger.PassengerViewModel
import com.example.volatus.ui.features.passenger.PassengerViewModelInterface
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class PassengerViewModelTests {
    private lateinit var viewModel:PassengerViewModelInterface
    private lateinit var sharedModel:SharedModel

    @Before
    fun setup(){
        viewModel = PassengerViewModel()
        sharedModel = SharedModel()
    }



    @Test fun `When onClick add passenger button, update passenger`() {

        viewModel.getPassengerList(sharedModel.passengerState.value.passengerList)
        viewModel.onAction(PassengerContract.OnAction.passengerCountPlus(0))
        viewModel.onAction(PassengerContract.OnAction.passengerCountPlus(1))
        viewModel.onAction(PassengerContract.OnAction.passengerCountPlus(2))

        val list = viewModel.uiState.value.passengerList

        //Adult
        val expectedAdultCount = list[0].count
        val expectedAdultMinusButton = list[0].minusButtonStatus

        //Child
        val expectedChildCount = list[1].count
        val expectedChildMinusButton = list[1].minusButtonStatus

        //Baby
        val expectedBabyCount = list[2].count
        val expectedBabyMinusButton = list[2].minusButtonStatus

        assertEquals(
            expectedAdultCount,
            2
        )
        assertFalse(expectedAdultMinusButton)


        assertEquals(
            expectedChildCount,
            1
        )
        assertFalse(expectedChildMinusButton)

        assertEquals(
            expectedBabyCount,
            1
        )
        assertFalse(expectedBabyMinusButton)

    }

    @Test
    fun `When onClick minus passenger button, control only Adult`(){

        viewModel.getPassengerList(sharedModel.passengerState.value.passengerList)
        viewModel.onAction(PassengerContract.OnAction.passengerCountPlus(0))
        viewModel.onAction(PassengerContract.OnAction.passengerCountMinus(0))

        val list = viewModel.uiState.value.passengerList
        val expectedAdultCount = list[0].count
        val expectedAdultMinusButton = list[0].minusButtonStatus

        // when count is 1, minusbutton will not click

        assertEquals(
            expectedAdultCount,
            1
        )
        assertFalse(expectedAdultMinusButton)
    }

    @Test fun `when on added passenger return passenger tex on sharedModel`(){
        viewModel.getPassengerList(sharedModel.passengerState.value.passengerList)
        viewModel.onAction(PassengerContract.OnAction.passengerCountPlus(0))
        viewModel.onAction(PassengerContract.OnAction.passengerCountPlus(1))

        val  expectedText = "2 Adult,1 Child"

        sharedModel.onAction(SharedContract.SharedAction.updatePassengerList(viewModel.uiState.value.passengerList))
        assertEquals(
            expectedText,
            sharedModel.passengerState.value.passengerText)
    }
}