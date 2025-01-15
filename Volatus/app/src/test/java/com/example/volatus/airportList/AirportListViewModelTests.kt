package com.example.volatus.airportList

import com.example.volatus.ui.features.airtportList.AirportListViewModel
import com.example.volatus.ui.features.airtportList.AirportListViewModelInterface
import org.junit.Before
import org.junit.Test
import com.example.volatus.R
import com.example.volatus.ui.features.airtportList.AirportContract
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.rules.TestRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.volatus.ui.features.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AirportListViewModelTests {
    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel:AirportListViewModelInterface
    private lateinit var service: MockAirportListService

    @Before
    fun setup(){
        Dispatchers.setMain(Dispatchers.Unconfined)
        service = MockAirportListService()
        viewModel = AirportListViewModel(service)
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
        viewModel.onAction(AirportContract.UiAction.onSearchAction("Airname"))
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

    @Test
    fun `when airport service throw error,return uiState listMessage`()  = runTest{
        service.mockAirportListError = true
        viewModel = AirportListViewModel(service)

        val uiState = withTimeoutOrNull(5000L){
            viewModel.uiState.value.listMessage
        }

        val expectedListMessage = Pair(true,R.string.errorMessage)
        assertEquals(uiState!!.second,expectedListMessage.second)
    }

}