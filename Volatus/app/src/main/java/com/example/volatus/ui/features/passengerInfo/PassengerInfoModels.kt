package com.example.volatus.ui.features.passengerInfo

import com.example.volatus.R
import java.time.LocalDate

data class InfoShow(
    val index: Int,
    val passengerTitle: String,
    var showState: Boolean
)

data class PassengerInfo(
    var id: Int,
    var trIdNumber: String = "",
    var name: String = "",
    var surname: String = "",
    var birthDate: LocalDate,
    // First:Start Secand : Finish
    var dateRange: Pair<LocalDate, LocalDate>
)

data class PassengerInfoError(

    var trIdNumberError: Pair<Int, Boolean> = Pair(R.string.emptyDefault, false),
    var nameError: Pair<Int, Boolean> = Pair(R.string.emptyDefault, false),
    var surnameError: Pair<Int, Boolean> = Pair(R.string.emptyDefault, false),
    var birthDateError: Pair<Int, Boolean> = Pair(R.string.emptyDefault, false),
)