package com.example.volatus.ui.theme

import com.example.volatus.R


data class ImageType(
    val image:Int,
    val contentDescription:Int
)

val worldMap = ImageType(
    image = R.drawable.background,
    contentDescription = R.string.worldMapContent
)


val swapLocationIcon = ImageType(
    image =  R.drawable.swap_icon,
    contentDescription = R.string.swapIconsContent
)