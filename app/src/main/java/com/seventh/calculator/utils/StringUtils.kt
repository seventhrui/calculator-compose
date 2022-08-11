package com.seventh.calculator.utils

fun String?.endWithMethod(): Boolean {
    return if (!this.isNullOrEmpty())
        this.endsWith("×") || this.endsWith("-") || this.endsWith("+") || this.endsWith("÷")
    else
        false
}

fun String.toNubmer(): Float {
    return this.toFloat()
}