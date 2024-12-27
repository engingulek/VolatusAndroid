package com.example.volatus.home

import androidx.compose.runtime.collectAsState
import com.example.volatus.ui.features.home.HomeViewModel
import com.example.volatus.ui.features.home.HomeViewModelInterface
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import com.example.volatus.R
import com.example.volatus.ui.features.home.HomeContract
import com.example.volatus.ui.features.home.HomeContract.TripeTypeData
import com.example.volatus.ui.theme.ImageType
import com.example.volatus.ui.theme.swapLocationIcon
import com.example.volatus.ui.theme.worldMap
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue

class HomeViewModelTest {
    private lateinit var viewModel:HomeViewModelInterface
    @Before
    fun setup(){
        viewModel = HomeViewModel()
    }


    @Test
    fun `When open home screen, return uiState`() {
        val uiState = viewModel.uiState.value

        val expectedBackImage = worldMap
        //Test Assertions
        assertEquals(
            "backImage is not correct",
            expectedBackImage,
            uiState.backImage
            )

        val expectedSwapIcon = swapLocationIcon
        assertEquals(
            "swapIcon is not correct",
            expectedSwapIcon,
            uiState.swapIcon
        )

        val expectedSearchButtonTitle = R.string.search
        assertEquals(
            "searchButtonTitle is not correct",
            expectedSearchButtonTitle,
            uiState.searchButtonTitle
        )
    }

    @Test
    fun `when open home screen ,return TripTypeState`(){
        val tripTypeState = viewModel.tripState.value

        val expectedOneWayTitle = R.string.oneway
        assertEquals(
            "oneWayTitle is not correct",
            expectedOneWayTitle,
            tripTypeState.oneWayTitle
        )
        val expectedRoundedTitle = R.string.roundTrip

        assertEquals(
            "roundedTitle is not correct",
            expectedRoundedTitle,
            tripTypeState.roundedTitle
        )

        val expectedOneWayTripeType = TripeTypeData(
            contentColor = R.color.white,
            containerColor = R.color.red
        )

        assertEquals(
            "oneWayTripeType is not correct",
            expectedOneWayTripeType,
            tripTypeState.oneWayTripeType
        )


        val expectedRoundedTripeType = TripeTypeData(
            contentColor = R.color.red,
            containerColor = R.color.white
        )

        assertEquals(
            "roundedTripeType is not correct",
            expectedRoundedTripeType,
            tripTypeState.roundedTripeType
        )

    }

    @Test
    fun `when open home screen, return LocationState`(){
        val locationState = viewModel.locationState.value
      val expectedFromTitle = R.string.from
        assertEquals(
            "fromTitle is not correct",
            expectedFromTitle,
            locationState.fromTitle
        )
      val expectedToTitle = R.string.to
        assertEquals(
            "toTitle is not correct",
            expectedToTitle,
            locationState.toTitle
        )

      val expectedFromLocation = "Choose"
        assertEquals(
            "fromLocation is not correct",
            expectedFromLocation,
            locationState.fromLocation
        )
      val expectedToLocation = "Choose"
        assertEquals(
            "toTitle is not correct",
            expectedToLocation,
            locationState.toLocation
        )
    }

    @Test
    fun `when open home screen, return DateState`(){
         val dateState = viewModel.dateState.value
        val expectedDepartureTitle = R.string.departure
        assertEquals(
            "departureTitle is not correct",
            expectedDepartureTitle,
            dateState.departureTitle
        )

        val expectedReturnTitle = R.string.returnTitle
        assertEquals(
            "returnTitle is not correct",
            expectedReturnTitle,
            dateState.returnTitle
        )

        assertTrue(dateState.returnVisible)

    }


    @Test
    fun `when onClick rounded trip button ,return TripTypeState`(){
        viewModel.onAction(HomeContract.UiAction.OnClickRoundedTrip)
        val tripTypeState = viewModel.tripState.value

        val expectedOneWayTripeType = TripeTypeData(
            contentColor = R.color.red,
            containerColor = R.color.white
        )

        assertEquals(
            "oneWayTripeType is not correct",
            expectedOneWayTripeType,
            tripTypeState.oneWayTripeType
        )


        val expectedRoundedTripeType = TripeTypeData(
            contentColor = R.color.white,
            containerColor = R.color.red
        )

        assertEquals(
            "roundedTripeType is not correct",
            expectedRoundedTripeType,
            tripTypeState.roundedTripeType
        )

    }

    @Test
    fun `when onClick one way  button ,return TripTypeState`(){
        viewModel.onAction(HomeContract.UiAction.OnClickOneWay)
        val tripTypeState = viewModel.tripState.value

        val expectedOneWayTripeType = TripeTypeData(
            contentColor = R.color.white,
            containerColor = R.color.red
        )

        assertEquals(
            "oneWayTripeType is not correct",
            expectedOneWayTripeType,
            tripTypeState.oneWayTripeType
        )


        val expectedRoundedTripeType = TripeTypeData(
            contentColor = R.color.red,
            containerColor = R.color.white
        )

        assertEquals(
            "roundedTripeType is not correct",
            expectedRoundedTripeType,
            tripTypeState.roundedTripeType
        )

    }

    @Test
    fun `when onClick rounded trip button ,return DateState`(){
        viewModel.onAction(HomeContract.UiAction.OnClickRoundedTrip)
        val dateState = viewModel.dateState.value
        assertFalse(dateState.returnVisible)
    }


    @Test
    fun `when onClick one way button ,return DateState`(){
        viewModel.onAction(HomeContract.UiAction.OnClickOneWay)
        val dateState = viewModel.dateState.value
        assertTrue(dateState.returnVisible)
    }


}