package com.example.volatus.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.volatus.R


data class ImageType(
    val image:Int,
    val contentDescription:Int
)

data class IconVectorType(
    val image:ImageVector,
    val contentDescription:Int,
    val color:Color,
    val enabledColor:Color

)

val worldMap = ImageType(
    image = R.drawable.background,
    contentDescription = R.string.worldMapContent
)


val swapLocationIcon = ImageType(
    image =  R.drawable.swap_icon,
    contentDescription = R.string.swapIconsContent
)

val minusIcon = IconVectorType(
    image = Icons.Default.RemoveCircle,
    contentDescription = R.string.minusIcon,
    color = Color.Red,
    enabledColor = Color.Red.copy(alpha = 0.5f)
)

val addIcon = IconVectorType(
    image = Icons.Default.AddCircle,
    contentDescription = R.string.addIcon,
    color = Color.Red,
    enabledColor = Color.Red
)