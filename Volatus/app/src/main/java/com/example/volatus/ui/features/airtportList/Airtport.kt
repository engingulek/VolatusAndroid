package com.example.volatus.ui.features.airtportList

import com.google.gson.annotations.SerializedName

data class Airport(
   @SerializedName("id") val id: Int,
   @SerializedName("country")   val country: String,
   @SerializedName("city")  val city: String,
   @SerializedName("code")   val code: String,
   @SerializedName("airname")  val airname: String

) {
}