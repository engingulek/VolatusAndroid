package com.example.volatus.airportList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.volatus.ui.features.airtportList.AirportListViewModel
import com.example.volatus.ui.features.airtportList.AirportListViewModelInterface
import org.junit.Before
import org.junit.Test
import com.example.volatus.R
import com.example.volatus.ui.features.airtportList.AirportContract
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue

class AirportListViewModelTests {
    private lateinit var viewModel:AirportListViewModelInterface

    @Before
    fun setup(){
        viewModel = AirportListViewModel()
    }


    @Test  fun `When open airport screen, return uiState`(){
        val uiState = viewModel.uiState.value

        val expectedAirportListTitle= R.string.airportListTitle

        assertEquals("airportListTitle is not correct",
            expectedAirportListTitle,
            uiState.airportListTitle)

        val expectedSearchPlaceholder = R.string.searchPlaceholder

        assertEquals("searchPlaceholder is not correct",
            expectedSearchPlaceholder,
            uiState.searchPlaceholder)


        val expectedListMessage = Pair(false,R.string.emptyDefault)
        assertFalse("expectedListMessage status is not correct",
           uiState.listMessage.first

            )
        assertEquals("expectedListMessage message is not correct",
            expectedListMessage.second,
            uiState.listMessage.second
        )
    }


    @Test
    fun `When search airport , return uiState listMessage`() {
        viewModel.onAction(AirportContract.UiAction.onSearchAction("istan"))
        val uiState = viewModel.uiState.value



        val expectedListMessage = Pair(false,R.string.emptyDefault)
        assertFalse("expectedListMessage status is not correct",
            uiState.listMessage.first

        )
        assertEquals("expectedListMessage message is not correct",
            expectedListMessage.second,
            uiState.listMessage.second
        )
    }

    @Test
    fun `When search airport , return uiState listMessage empty`() {
        viewModel.onAction(AirportContract.UiAction.onSearchAction("dsadsa"))
        val uiState = viewModel.uiState.value



        val expectedListMessage = Pair(true,R.string.listAirportEmpty)
        assertTrue("expectedListMessage status is not correct",
            uiState.listMessage.first

        )
        assertEquals("expectedListMessage message is not correct",
            expectedListMessage.second,
            uiState.listMessage.second
        )
    }

}