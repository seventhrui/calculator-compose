package com.seventh.calculator.utils

import java.util.regex.Pattern

object CalculatorUtils {
    fun dispatch(params: String): String {
        var result: String = "计算错误"
        val array: List<String> = subNumber(params)

        if (params.contains("+")) {
            result = add(array)
        } else if (params.contains("-")) {
            result = subtract(array)
        } else if (params.contains("×")) {
            result = multiply(array)
        } else if (params.contains("÷")) {
            result = divide(array)
        }
        return result
    }

    fun add(params: List<String>): String {
        return "${params[0].toNubmer() + params[1].toNubmer()}"
    }

    fun subtract(params: List<String>): String {
        return "${params[0].toNubmer() - params[1].toNubmer()}"
    }

    fun multiply(params: List<String>): String {
        return "${params[0].toNubmer() * params[1].toNubmer()}"
    }

    fun divide(params: List<String>): String {
        return "${params[0].toNubmer()/(params[1].toNubmer())}"
    }

    fun subNumber(params: String): List<String> {
        return params.split(Pattern.compile("[-+×÷]"))
    }
}