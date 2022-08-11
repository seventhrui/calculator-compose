package com.seventh.calculator.data

import androidx.compose.ui.graphics.Color

data class KeyBean(
    val title: String = "",
    val type: KeyType = KeyType.NUMBER,
    val backColor: Color = Color.White,
    val textColor: Color = Color.Black
)

enum class KeyType {
    NUMBER, METHOD
}
