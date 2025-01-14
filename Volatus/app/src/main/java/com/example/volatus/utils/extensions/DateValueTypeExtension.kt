package com.example.volatus.utils.extensions

import androidx.compose.ui.graphics.Color
import com.example.volatus.ui.features.date.DateValueType

fun DateValueType.getDateColor() : Color {
    when(this) {
        DateValueType.NOw -> return  Color.White
        DateValueType.DISABLE -> return  Color.Gray
        DateValueType.DefaultDate -> return  Color.Black
        DateValueType.Selected -> return  Color.White
        DateValueType.Between -> return Color.White
    }
}


fun DateValueType.getDateCardColor() : Color {
    when(this) {
        DateValueType.NOw -> return  Color.Black
        DateValueType.DISABLE -> return  Color.Transparent
        DateValueType.DefaultDate -> return  Color.Transparent
        DateValueType.Selected -> return  Color.Red
        DateValueType.Between -> return  Color.Red.copy(alpha = 0.5f)
    }
}