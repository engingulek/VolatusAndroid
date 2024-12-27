package com.example.volatus.ui.features.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.volatus.R
import com.example.volatus.ui.theme.ImageType
import kotlinx.coroutines.flow.StateFlow

interface HomeViewModelInterface{
    var uiState : State<HomeContract.UiState>

}


class HomeViewModel : ViewModel(), HomeViewModelInterface{

    private val _uiState = mutableStateOf(HomeContract.UiState())
    override  var uiState : State<HomeContract.UiState> = _uiState




}